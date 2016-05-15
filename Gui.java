package heap;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import heap.BinomialHeap.HeapNode;
public class Gui {
    private String file="/home/itai/IdeaProjects/Software1/src/heap/Setting.txt";
    private JFrame mainFrame;
    private JPanel main;
    private JPanel main2;
    //private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JPanel controlPanel2;
    //private JPanel tree;
    private BinomialHeap x;
    private JTextArea nodetochange;
    private JTextArea asString;
    private JPanel[] panels;
    private JPanel[] panels2;
    private final int levels=8;
    private int counter;
    private int stop;
    private boolean autoplay=true;
    public Gui(BinomialHeap x){
        this.x=x;
        this.counter=0;
        prepareGUI();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        int stop=0;
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                    if(line.startsWith("delete:") || line.startsWith("insert:")){
                        stop+=line.split(":")[1].split(" ").length;
                    }else if(line.startsWith("decreasekey:")){
                        stop+=line.split(":")[1].split(" ").length/2;
                    }

                break;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.stop=stop;

    }

    public static void main(String[] args){
        BinomialHeap x=new BinomialHeap();
       /*int count=0;
       for(int i=1;i<11;i++){
           count=0;
           for(int j=0;j<10000*i;j++){
               int insert =(int)(1000000*Math.random())+1;
               while(x.search(insert)!=null){
                   insert =(int)(1000000*Math.random())+1;
               }
               count+=x.insert(insert," s");
           }
           System.out.println("insert i:"+i+" is: "+(double)count/(10000*i)+" size "+x.size());
           count=0;
           for(int j=0;j<10000*i;j++){
               if(x.empty()){
                   System.out.println("empty");
               }else {
                   count += x.delete(x.getRoot().getKey());
               }
           }
           System.out.println("delete i:"+i+" is: "+(double)count/(10000*i));
       }*/

        //x.insert(1);
        //for(int i=0;i<15;i++){
        //    x.insert((int)(10000*Math.random())+1," s");
        //}
        HeapNode root = x.head;
        Gui swingControlDemo = new Gui(x);

    }

    private void prepareGUI(){
        mainFrame = new JFrame("Heap");
        mainFrame.setSize(800,900);

        GridLayout layout = new GridLayout(5, 1);
        layout.setVgap(2);
        mainFrame.setLayout(layout);

        //mainFrame.setLayout(new BoxLayout(mainFrame, BoxLayout.Y_AXIS));

        statusLabel = new JLabel("HELLO",JLabel.CENTER);

        statusLabel.setSize(100,100);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel2 = new JPanel();
        controlPanel2.setLayout(new FlowLayout());

        mainFrame.add(controlPanel);
        //controlPanel.set
        mainFrame.add(controlPanel2);
        mainFrame.add(statusLabel);

        update();
        copy_before();

        mainFrame.setVisible(true);
        
        showEventDemo();
    }

    public Color choosecolor(int p){
        if(p%3==0){
            return Color.CYAN;
        }else if(p%3==1){
            return Color.YELLOW;
        }
        return Color.ORANGE;
    }

    public void update() {

        HeapNode[][] nodes=new HeapNode[15][];
        for (int i = 0; i < levels &&panels!=null; i++) {
            if(main!=null && panels[i]!=null) {
                main.remove(panels[i]);
            }
        }
        if(main!=null) {
            mainFrame.remove(main);
        }
        main=new JPanel();
        //main.setSize(500,500);
        panels=new JPanel[15];
        int count =0 ;
        nodes[0]=new HeapNode[20];
        //nodes[0][0]=x.head;
        HeapNode h=x.head;
        panels[0]=new JPanel();
        panels[0].setLayout(new GridLayout(1,20));
        for(int p=0;p<20 && h!=null;p++) {
            nodes[0][p] = h;
            panels[0].add(nodetoJButton(h,choosecolor(p)));

            System.out.print("began h1:"+h.key+" \n");
            h=h.sibling;
        }
        main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));
        main.add(panels[0]);
        for(int i=1;i<levels;i++){
            //nodes[i]=new HeapNode[(int)(20*Math.pow(3,i))];
            nodes[i]=new HeapNode[20];
            int counter=0;
            panels[i] = new JPanel();
            panels[i].setLayout(new GridLayout(1, nodes[i].length+1));
            for(int j=0;j<nodes[i-1].length;j++){
                if(nodes[i-1][j]!=null && nodes[i-1][j].child!=null){
                    HeapNode h1 =nodes[i-1][j].child;
                    while(h1!=null){
                        if(counter>=nodes[i].length){
                            System.out.println("tree too big for gui!!");
                        }
                        nodes[i][counter]=h1;
                        JButton p2=nodetoJButton(h1);
                        Color choose=Color.GREEN;
                        for(int mo=0;mo<nodes[i-1].length && mo<panels2[i-1].getComponentCount();mo++) {
                            if(nodes[i-1][mo]!=null && nodes[i-1][mo].key==h1.parent.key){
                                choose=panels[i-1].getComponent(mo).getBackground();
                            }
                        }
                        p2.setBackground(choose);
                        panels[i].add(p2);
                        h1=h1.sibling;
                        counter++;
                    }
                }
            }
            main.add(panels[i]);
        }
        mainFrame.add(main);
    }

    public void copy_before(){
        mainFrame.remove(statusLabel);
        HeapNode[][] nodes2=new HeapNode[15][];
        for (int i = 0; i < levels && panels2!=null; i++) {
            if(main2!=null && panels2[i]!=null) {
                main2.remove(panels2[i]);
            }
        }
        if(main2!=null) {
            mainFrame.remove(main2);
        }
        main2=new JPanel();
        //main2.setSize(500,500);
        panels2=new JPanel[15];
        int count =0 ;
        nodes2[0]=new HeapNode[20];
        //nodes[0][0]=x.head;
        HeapNode h=x.head;
        panels2[0]=new JPanel();
        panels2[0].setLayout(new GridLayout(1,20));
        for(int p=0;p<20 && h!=null;p++) {
            nodes2[0][p] = h;
            panels2[0].add(nodetoJButton(h));
            h=h.sibling;
        }
        main2.setLayout(new BoxLayout(main2,BoxLayout.Y_AXIS));
        main2.add(panels2[0]);

        for(int i=1;i<levels;i++){
            //nodes2[i]=new HeapNode[(int)(20*Math.pow(3,i))];
            nodes2[i]=new HeapNode[20];
            int counter=0;
            panels2[i] = new JPanel();
            panels2[i].setLayout(new GridLayout(1, nodes2[i].length+1));
            for(int j=0;j<nodes2[i-1].length;j++){
                if(nodes2[i-1][j]!=null && nodes2[i-1][j].child!=null){
                    HeapNode h1 =nodes2[i-1][j].child;
                    while(h1!=null){
                        if(counter>=nodes2[i].length){
                            System.out.println("tree too big for gui!!");
                        }
                        //System.out.println("added "+h1.key +" to row #"+i+"(i starts at 0)");
                        nodes2[i][counter]=h1;

                        JButton p2=nodetoJButton(h1);
                        //Color choose=Color.GREEN;
                        //if(panels2[i-1].getComponentCount()>j) {
                        //    choose = panels2[i - 1].getComponent(j).getBackground();
                        //}
                        /*for(int mo=0;mo<nodes2[i-1].length && mo<panels2[i-1].getComponentCount();mo++) {
                            if(nodes2[i-1][mo]!=null && nodes2[i-1][mo].key==h1.parent.key){
                                choose=panels2[i-1].getComponent(mo).getBackground();
                            }
                        }*/
                        //p2.setBackground(choose);
                        panels2[i].add(p2);

                        h1=h1.sibling;
                        counter++;
                    }
                }
            }
            //panels[i][0].get
            //panels[i].getComponent(1).getX
            main2.add(panels2[i]);
        }
        mainFrame.add(main2);
        mainFrame.add(statusLabel);
    }

    public JButton nodetoJButton(HeapNode c){
        String l=c.key+"";
        if(c.parent!=null){
            l+=" p:"+c.parent.key;
        }
        JButton a =new JButton(l);
        a.setActionCommand(c.key+"");
        a.addActionListener(new ButtonClickListener());
        //a.setBackground(Color.YELLOW);
        //a.setSize(50,50);
        return a;
    }

    public JButton nodetoJButton(HeapNode c,Color c1){
        String l=c.key+"";
        if(c.parent!=null){
            l+=" p:"+c.parent.key;
        }
        JButton a =new JButton(l);
        a.setActionCommand(c.key+"");
        a.addActionListener(new ButtonClickListener());
        a.setBackground(c1);
        //a.setSize(50,50);
        return a;
    }


    private void showEventDemo(){
        //headerLabel.setText("Control in action: Button");
        nodetochange = new JTextArea("Num");
        nodetochange.setName("text");
        nodetochange.setEditable(true);
        if(x.head!=null) {
            nodetochange.setText(x.head.key+"");
        }
        controlPanel.add(nodetochange);
        String[]func={"insert","delete","update","deleteMin","print","findMin","play"};
        for(int i=0;i<func.length;i++){
            JButton f=new JButton(func[i]);
            f.setActionCommand(func[i]);
            f.addActionListener(new ButtonClickListener());
            controlPanel.add(f);
        }
        
        String[]func1={"size","arrayToHeap","minTreeRank","binaryRep","decrease key","valid"};
        for(int i=0;i<func1.length;i++){
            JButton f=new JButton(func1[i]);
            f.setActionCommand(func1[i]);
            f.addActionListener(new ButtonClickListener());
            controlPanel2.add(f);
        }



        mainFrame.setVisible(true);

        if(autoplay){
            for(int i=0;i<5;i++){
                nextCommand();
            }
        }

    }

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            String c=command.trim();
            statusLabel.setText(command+" clicked");
            if(command.equals("insert")){
                if(Integer.parseInt(nodetochange.getText())==12){
                    int p=54;
                }
                insert12(Integer.parseInt(nodetochange.getText()));
            }else if(command.equals("delete")){
                delete12(Integer.parseInt(nodetochange.getText()));
            }else if(command.equals("update")){
                update();
            }else if(command.equals("deleteMin")){
                x.deleteMin();
                update();
            }else if(command.equals("findMin")){
                statusLabel.setText("min is:"+x.findMin());
                //update();
            }else if(command.equals("play")){
                nextCommand();
                //update();
            }else if(command.equals("valid")){
                //nextCommand();
                statusLabel.setText("is valid:"+x.isValid()+"");
                //update();
            }else if(command.equals("print")){
                x.print();
                //update();
            }else if(command.equals("size")){
                if(nodetochange.getText().equals("All")){
                    statusLabel.setText("size all:"+x.size());
                }else{
                    HeapNode t=x.search(Integer.parseInt(nodetochange.getText()));
                    statusLabel.setText("size of "+t.key+" is " +x.size(t));

                }
                //,"decrease key"}
            }else if(command.equals("arrayToHeap")){
                arraytoheap12();
            }else if(command.equals("minTreeRank")){
                statusLabel.setText("minranktree: "+x.minTreeRank());
            }else if(command.equals("binaryRep")){
                String s=" ";
                if(x.binaryRep() ==null){
                    s="no a valid heap";
                }else {
                    for (boolean y : x.binaryRep()) {
                        if (y) {
                            s += "1 ";
                        } else {
                            s += "0 ";
                        }

                    }
                }
                statusLabel.setText("binaryRep:"+s);
            }else if(command.equals("decrease key")){
                int z=Integer.parseInt(nodetochange.getText().split(" ")[0]);
                int y=Integer.parseInt(nodetochange.getText().split(" ")[1]);
                decreaseKey(z,y);
            }else{
                nodetochange.setText(command);
            }
        }
    }

    public void arraytoheap12(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        String line = null;
        try {
            line=reader.readLine();
            while (line != null) {
                if(line.contains("arrayToHeap")){
                    String [] arr12=line.split(":")[1].split(" ");
                    int [] ints=new int[arr12.length];
                    for(int m=0;m<arr12.length;m++){
                        ints[m]=Integer.parseInt(arr12[m]);
                    }
                    x.arrayToHeap(ints);
                    break;
                }
                line=reader.readLine();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void decreaseKey(int z,int y){
        copy_before();
        x.decreaseKey(z,y);
        update();
    }

    public void insert12(int key){
        copy_before();
        x.insert(key);
        update();
    }

    public void delete12(int key){
        copy_before();
        x.delete(key);
        update();

    }

    public void nextCommand(){
            stop=0;
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    if(line.startsWith("delete:") || line.startsWith("insert:")){
                        if(counter==3){
                            System.out.println("debug");
                        }
                        stop+=line.split(":")[1].split(" ").length;
                        if(stop>counter && counter>=stop-line.split(":")[1].split(" ").length){
                            int p=stop-line.split(":")[1].split(" ").length;
                            p=(counter-p);
                            if(line.startsWith("delete:")){
                                System.out.println(" counter delete:"+counter+" stop:"+stop);
                                delete12(Integer.parseInt(line.split(":")[1].split(" ")[p]));
                            }else{
                                System.out.println(" counterL:"+counter+" stop:"+stop);
                                insert12(Integer.parseInt(line.split(":")[1].split(" ")[p]));
                            }
                            counter++;
                            break;
                        }
                    }else if(line.startsWith("decreasekey:")){
                        stop+=line.split(":")[1].split(" ").length/2;
                        if(stop>counter && counter>=stop-line.split(":")[1].split(" ").length/2){
                            int stop2=stop-line.split(":")[1].split(" ").length/2;
                            int z =Integer.parseInt(line.split(":")[1].split(" ")[(counter-stop2)*2]);
                            int y =Integer.parseInt(line.split(":")[1].split(" ")[(counter-stop2)*2+1]);
                            decreaseKey(z,y);
                            counter++;
                            break;
                        }
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
    }

}
