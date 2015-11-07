
public class Frame extends java.awt.Frame 
{
    
    public Frame() {
	        initComponents();
	    }
    
    
    private void initComponents() {

	        jLayeredPane1 = new javax.swing.JLayeredPane();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jList1 = new javax.swing.JList();
	        scrollbar1 = new java.awt.Scrollbar();
	        label1 = new java.awt.Label();
	        button1 = new java.awt.Button();
	        button2 = new java.awt.Button();
	        jTextField1 = new javax.swing.JTextField();
	        button3 = new java.awt.Button();
	        label2 = new java.awt.Label();
	        label3 = new java.awt.Label();
	        label4 = new java.awt.Label();
	        button4 = new java.awt.Button();
	        button5 = new java.awt.Button();

	        setMinimumSize(new java.awt.Dimension(600, 400));
	        addWindowListener(new java.awt.event.WindowAdapter() {
	            public void windowClosing(java.awt.event.WindowEvent evt) {
	                exitForm(evt);
	            }
	        });

	        jLayeredPane1.setMinimumSize(new java.awt.Dimension(600, 600));
	        jLayeredPane1.setPreferredSize(new java.awt.Dimension(600, 400));
	        jLayeredPane1.setVerifyInputWhenFocusTarget(false);

	        jList1.setModel(new javax.swing.AbstractListModel() {
	            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
	            public int getSize() { return strings.length; }
	            public Object getElementAt(int i) { return strings[i]; }
	        });
	        jScrollPane1.setViewportView(jList1);

	        jLayeredPane1.add(jScrollPane1);
	        jScrollPane1.setBounds(460, 100, 120, 260);
	        jLayeredPane1.add(scrollbar1);
	        scrollbar1.setBounds(580, 100, 16, 260);

	        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
	        label1.setText("A?ocuy");
	        jLayeredPane1.add(label1);
	        label1.setBounds(488, 60, 100, 30);
	        label1.getAccessibleContext().setAccessibleName("Friends");

	        button1.setLabel("Aiaaaeou");
	        jLayeredPane1.add(button1);
	        button1.setBounds(460, 360, 70, 24);
	        button1.getAccessibleContext().setAccessibleName("Add");

	        button2.setLabel("Oaaeeou");
	        jLayeredPane1.add(button2);
	        button2.setBounds(527, 360, 70, 24);
	        button2.getAccessibleContext().setAccessibleName("Delete");
	        button2.getAccessibleContext().setAccessibleDescription("");

	        jTextField1.setText("Niiauaiea");
	        jLayeredPane1.add(jTextField1);
	        jTextField1.setBounds(40, 100, 390, 260);

	        button3.setLabel("Ioi?aaeou");
	        jLayeredPane1.add(button3);
	        button3.setBounds(320, 360, 110, 40);

	        label2.setText("Iee a?oaa");
	        jLayeredPane1.add(label2);
	        label2.setBounds(50, 60, 120, 30);

	        label3.setText("A?aiy iauaiey");
	        jLayeredPane1.add(label3);
	        label3.setBounds(480, 10, 100, 30);

	        label4.setText("IP a?oaa");
	        jLayeredPane1.add(label4);
	        label4.setBounds(200, 60, 150, 30);

	        button4.setLabel("Niaaeieouny");
	        jLayeredPane1.add(button4);
	        button4.setBounds(50, 10, 110, 40);

	        button5.setLabel("?acuaaeieouny");
	        jLayeredPane1.add(button5);
	        button5.setBounds(230, 10, 120, 40);

	        add(jLayeredPane1, java.awt.BorderLayout.CENTER);

	        pack();
	    }// </editor-fold>                        

	    /**
	     * Exit the Application
	     */
	    private void exitForm(java.awt.event.WindowEvent evt) {                          
	        System.exit(0);
	    }                         

	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String args[]) {
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new Frame().setVisible(true);
	            }
	        });
	    }


	    // Variables declaration - do not modify                     
	    private java.awt.Button button1;
	    private java.awt.Button button2;
	    private java.awt.Button button3;
	    private java.awt.Button button4;
	    private java.awt.Button button5;
	    private javax.swing.JLayeredPane jLayeredPane1;
	    private javax.swing.JList jList1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTextField jTextField1;
	    private java.awt.Label label1;
	    private java.awt.Label label2;
	    private java.awt.Label label3;
	    private java.awt.Label label4;
	    private java.awt.Scrollbar scrollbar1;
    
    
    
}
