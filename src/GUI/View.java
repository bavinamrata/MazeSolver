/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Algorithm.DFS;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.event.*;


/**
 *
 * @author NAMI
 * Values: 0= not Visited
 *         1= blocked wall
 *         2= Visited
 *         9= Target
 * 
 */
public class View extends JFrame implements ActionListener, MouseListener{
    private int[][] maze=
        {
            {1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,1,0,1,0,0,0,0,0,1},      //0is starting point
            {1,0,1,0,0,0,1,0,1,1,1,0,1},
            {1,0,0,0,1,1,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,0,0,1},
            {1,0,1,0,1,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,1,9,1},   //9 is target to reach location in maze
            {1,1,1,1,1,1,1,1,1,1,1,1,1}
};
    private int[]target={8,11};  //seletct target, target is still upto 9 so 8,11(row,coloum) 
    private List<Integer> path=new ArrayList<>();   //make it simple we not use <list<list> list of list for2d-array
    //traversing array pair of 2 arraylis{{},{}}
    
    JButton submitButton;
    JButton clearButton;
    JButton cancelButton;
    
    
    public View(){
        this.setTitle("Mazesolver");
        this.setSize(520, 500);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // close in direct if not set this method gui open in taskbar
        this.setLocationRelativeTo(null);  //windows open middle on screen
         
        submitButton=new JButton("Sumbit");   //button structer
        submitButton.addActionListener(this); 
        submitButton.setBounds(80, 400, 80, 30);   
        
        clearButton=new JButton("Clear");
        clearButton.addActionListener(this); 
        clearButton.setBounds(200, 400, 80, 30); //Button structer
        
        cancelButton=new JButton("Cancel");
        cancelButton.addActionListener(this); 
        cancelButton.setBounds(320, 400, 80, 30); //button structer
        
        this.addMouseListener(this); //mouselistner add
        
        this.add(cancelButton);
        this.add(clearButton);    //add button structer 
        this.add(submitButton);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(int row=0;row<maze.length;row++)
        {
            for(int col=0;col<maze[0].length;col++)
            {
                Color color;   ///Color is class nd color is object for black,white,red color in maze
                switch(maze[row][col]){
                    case 1: color=Color.BLACK; break;
                    case 9:color=Color.RED; break;
                    default:color=Color.WHITE;break;
                }
               g.setColor(color);  //color properties set
               g.fillRect(40*col, 40*row, 40, 40);     //for adding nd coloring block
               g.setColor(Color.BLACK);
               g.drawRect(40*col, 40*row, 40, 40);    // square brock or white block differentiation using black line
                
            }
        }
        for(int p=0;p<path.size();p+=2)  //for paint function p+=2 bcoz every set include 2 cordinate xy
        {
            int pathX=path.get(p);
            int pathY=path.get(p+1);
            g.setColor(Color.GREEN);
            g.fillRect(40*pathY, 40*pathX, 40,40);
        }
    }
     @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==submitButton){
                try{
                    DFS.searchPath(maze, 1, 1, path);
                    this.repaint();
                }
                 catch(Exception exce){
                        JOptionPane.showMessageDialog(null, exce.toString());
            }
            }
            if(e.getSource()==clearButton){
                path.clear();
            for(int row=0;row<maze.length;row++){
                for(int col=0;col<maze[0].length;col++)
                {
                    if(maze[row][col]==2){
                        maze[row][col]=0;
                    }
                }
            }
            this.repaint();
            }

             if(e.getSource()==cancelButton){

                 int flag=JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Quit", "Sumbit", JOptionPane.YES_NO_OPTION);
                 if(flag==0)
                 {
                     System.exit(flag);      //gui close if flag==0
                 }
             }
        }
           public void mouseClicked(MouseEvent e){
               if(e.getX()>=0 && e.getX()<=maze[0].length*40 && e.getY()>=0 && e.getY()<=maze.length*40)  {
                   int row=e.getY()/40;
                   int col=e.getX()/40;
                   
                   if(maze[row][col]==1){
                       return;
                   }
                   Graphics g=getGraphics();
                   g.setColor(Color.WHITE);
                   g.fillRect(40*target[1], 40*target[0], 40, 40);
                   g.setColor(Color.red);
                   g.fillRect(col*40, row*40, 40, 40); 
                   maze[target[0]][target[1]]=0;
                   maze[row][col]=9;
                   target[0]=row;
                   target[1]=col;
               }
           }
           public void mousePressed(MouseEvent e){
               
           }
           public void mouseReleased(MouseEvent e){
               
           }
           public void mouseEntered(MouseEvent e){
               
           }
           public void mouseExited(MouseEvent e){
               
           }
                    
    public static void main(String[] args) {
        View gui=new View();
        gui.setVisible(true); //by default it false
    }
}
