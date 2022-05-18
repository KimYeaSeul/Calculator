package com.cal;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Calculator extends JFrame {
	// 화면 구현
	private final int width = 240;
	private final int height = 370;
	public JTextField inputSpace= new JTextField();
	
	private GridBagLayout grid = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	TitledBorder tB = new TitledBorder(new LineBorder(Color.gray, 1));

//	ButtonActionListener bal = new ButtonActionListener();
	
	public ArrayList<String> equation = new ArrayList<String>();
	String num = "";
	private String prev_operation = "";
	
	public Calculator() {
		setLayout(null);
		
		inputSpace.setEditable(false); // 편집 불가능
		inputSpace.setBackground(Color.lightGray); // 배경은 화이트
		inputSpace.setHorizontalAlignment(JTextField.RIGHT);  // 정렬 위치
		inputSpace.setFont(new Font("Times", Font.BOLD, 50)); // 글씨 체
		inputSpace.setBounds(0, 0, width, 70); // x:8, y:10 위치 270x70 크기
		inputSpace.setBorder(new LineBorder(Color.gray, 0));
		
		JPanel buttonPanel = new JPanel();
//		buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); // 격자형태로 배열
		buttonPanel.setLayout(grid);
		buttonPanel.setBounds(0, 70, width, 274);
		
		String button_names[] = {"C", "±","%", "÷", "7", "8", "9", "x","4", "5", "6", "-", "1", "2", "3","+","0", ".", "="};
		String preference = "÷+=x-";
		JButton buttons[] = new JButton[button_names.length];
		
		  //======
			gbc.fill = GridBagConstraints.BOTH; // 꽉 채워줌
	        gbc.weightx = 1.0; // x축 안 넘어감
	        gbc.weighty = 1.0;// y축 안 넘어감
	        //========
		 int x = 0;
		 int y = 0;
		for(int i = 0; i<button_names.length; i++) {
			buttons[i] = new JButton(button_names[i]);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
			
			if(preference.contains(button_names[i])) {
				buttons[i].setBackground(new Color(255, 159, 9));
			}
//			System.out.println("x : "+ x + "y : "+ y);
			if(button_names[i] == "0") {
				make(buttons[i], x, y, 2, 1);
				x++;
			}else {
				make(buttons[i], x, y, 1, 1);
			}
			
			x++;
			if(x > 3) {
				x = 0;
				y++;
			}
//			
			buttons[i].addActionListener(new PadActionListener());
//			
			buttons[i].setBorder(tB);
			buttonPanel.add(buttons[i]);
			buttons[i].setOpaque(true);
		}
		
		add(inputSpace);
		add(buttonPanel);
		
		setTitle("계산기");
		setVisible(true);
		setSize(width, height);
		setBackground(Color.gray);
		setLocationRelativeTo(null); // 화면 가운데에 띄우기
		setResizable(false); // 사이즈조절 불가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을때 실행중인 프로그램 도 종료
	}

	public void make(JButton c, int x , int y, int w, int h) {
		gbc.gridy = y;
		gbc.gridx = x;
		gbc.gridheight = h;
		gbc.gridwidth = w;
		grid.setConstraints(c, gbc);
	}
	
	class PadActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { // 이벤트 처리!
			String operation = e.getActionCommand(); // 어떤 버튼이 눌렸나.
			if(operation.equals("C")) {
				inputSpace.setText("");
			}else if( operation.equals("=")) {
				String result = Double.toString(calculate(inputSpace.getText()));
				inputSpace.setText(""+result);
				num = "";
			} else if(operation.contentEquals("+") || operation.contentEquals("-") || operation.contentEquals("x") || operation.contentEquals("÷")){
				if(inputSpace.getText().equals("") && operation.equals("-")) {
					inputSpace.setText(inputSpace.getText() + e.getActionCommand());
				}else if(!inputSpace.getText().equals("") && !prev_operation.contentEquals("+") && !prev_operation.contentEquals("-") && !prev_operation.contentEquals("x") && !prev_operation.contentEquals("÷")) {
					inputSpace.setText(inputSpace.getText() + e.getActionCommand());
				}
			} else {
				inputSpace.setText(inputSpace.getText() + e.getActionCommand());
			}
			prev_operation = e.getActionCommand();
		}
	}
	// 연산자 중복 입력 불가, 연산기호 우선순위
	private void fullTextParsing(String inputText) {
		equation.clear();
		for(int i=0; i<inputText.length(); i++) {
			char ch = inputText.charAt(i);
			
			if(ch == '-' || ch == '+' || ch == 'x' || ch== '÷') {
				equation.add(num);
				num ="";
				equation.add(ch+"");
			}else {
				num = num + ch;
			}
		}
		equation.add(num);
		equation.remove("");
	}
	
	public double calculate(String inputText) {
		fullTextParsing(inputText);
		
		double prev = 0;
		double current = 0;
		String mode = "";
		
		for(String s : equation) {
			if(s.equals("+")){
				mode = "add";
			}else if(s.equals("-")){
				mode = "sub";
			}else if(s.equals("x")) {
				mode = "mul";
			}else if(s.equals("÷")) {
				mode = "div";
			}else {
				current = Double.parseDouble(s);
				if(mode == "add") {
					prev += current;
				}else if( mode == "sub") {
					prev -= current;
				}else if( mode == "mul") {
					prev *= current;
				}else if( mode == "div") {
					prev /= current;
				}else {
					prev = current;
				}
			}
			prev = Math.round(prev * 1000000)/1000000.0;
		}
		return prev;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Calculator();
	}

}
