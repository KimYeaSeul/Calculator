package com.cal;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Calculator extends JFrame {
	// 화면 구현
	
//	Container con = getContentPane();
	private final int width = 240;
	private final int height = 370;
	public JTextField inputSpace= new JTextField();
	
	private GridBagLayout grid = new GridBagLayout();
	  private GridBagConstraints gbc = new GridBagConstraints();
	  private Color darkColor = new Color(80, 82, 85);
//	  private LineBorder border = new LineBorder(darkColor);
	  TitledBorder tB = new TitledBorder(new LineBorder(darkColor, 1));

	String button_names[] = {"C", "±","%", "÷", "7", "8", "9", "x","4", "5", "6", "-", "1", "2", "3","+","0", ".", "="};
	String buttonString = "C±%÷789x456-123+0.=";
	JButton buttons[] = new JButton[button_names.length];
	MouseActionListener me = new MouseActionListener();
	
	
	
//	ButtonActionListener bal = new ButtonActionListener();
	
//	public ArrayList<String> equation = new ArrayList<>();
	
	Stack<Double> numStack = new Stack<>();
	Stack<Character> opStack= new Stack<>();
	
	String num = "";
	private String prev_operation = "";
	
	public Calculator() {
		
		setLayout(null);

//		setUndecorated(true);
//		con.setBackground(Color.GREEN);
		
		inputSpace.setEditable(false); // 편집 불가능
		inputSpace.setBackground(darkColor); // 배경은 화이트
		inputSpace.setHorizontalAlignment(JTextField.RIGHT);  // 정렬 위치
		inputSpace.setFont(new Font("Dialog", Font.PLAIN, 40)); // 글씨 체
		inputSpace.setBounds(0, 0, width, 70); // x:8, y:10 위치 270x70 크기
		inputSpace.setBorder(new LineBorder(Color.gray, 0));
		inputSpace.setForeground(Color.white);
		
		JPanel buttonPanel = new JPanel();

/*
		inputSpace = new JTextField();
		inputSpace.setEditable(false); // 편집 불가능
		inputSpace.setBackground(Color.WHITE); // 배경은 화이트
		inputSpace.setHorizontalAlignment(JTextField.RIGHT);  // 정렬 위치
		inputSpace.setFont(new Font("Times", Font.BOLD, 50)); // 글씨 체
		inputSpace.setBounds(8, 10, 270, 70); // x:8, y:10 위치 270x70 크기

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); // 격자형태로 배열
		buttonPanel.setBounds(8, 90, 270, 235);

		String button_names[] = {"C","÷", "+","=", "7", "8", "9", "x","4", "5", "6", "-", "1", "2", "3","0"};
		JButton buttons[] = new JButton[button_names.length];

		for(int i = 0; i<button_names.length; i++) {
			buttons[i] = new JButton(button_names[i]);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
			if(button_names[i] == "C") buttons[i].setBackground(Color.red);
			else if((i >=4 && i<=6) || (i >= 8 && i<=10) || (i >=12 && i<=14)) buttons[i].setBackground(Color.black);
			else buttons[i].setBackground(Color.gray);
			
			buttons[i].setForeground(Color.white); // 글자 색상 설정
			buttons[i].setBorderPainted(false); // 테두리 없애주기

//			buttons[i].addActionListener(new ButtonActionListener());

			buttonPanel.add(buttons[i]);
//			buttons[i].setOpaque(true); // 맥에서 배경색이 먹지 않는것을 해결!
		}
		
		add(inputSpace);
		add(buttonPanel);

		setTitle("계산기");
		setVisible(true);
		setSize(300, 370);
		setLocationRelativeTo(null); // 화면 가운데에 띄우기
		setResizable(false); // 사이즈조절 불가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을때 실행중인 프로그램 도 종료
		*/
		// 버튼 레이어 셋팅
		buttonPanel.setLayout(grid);
		buttonPanel.setBounds(0, 70, width, 274);
		buttonPanel.setBackground(darkColor);
		
	  //======
		gbc.fill = GridBagConstraints.BOTH; // 꽉 채워줌
        gbc.weightx = 1.0; // x축 안 넘어감
        gbc.weighty = 1.0;// y축 안 넘어감
        //========
		 int x = 0;
		 int y = 0;
		for(int i = 0; i<button_names.length; i++) {
			buttons[i] = new JButton(button_names[i]);
			buttons[i].setFont(new Font("Dialog", Font.BOLD, 20));
			buttons[i].setForeground(Color.white);
//			if(preference.contains(button_names[i])) {

			// 버튼 색
			if(button_names[i].matches("[÷+=x-]")) {
				buttons[i].setBackground(new Color(255, 159, 9));
			}else if(button_names[i].matches("[C±%]")) {
				buttons[i].setBackground(new Color(97, 99, 102));
			}else {
				buttons[i].setBackground(new Color(123, 125, 127));
			}
			
			// 격자 형태 생성 ======
			if(button_names[i] == "0") {
				makeFrame(buttons[i], x, y, 2, 1);
				x++;
			}else {
				makeFrame(buttons[i], x, y, 1, 1);
			}
			
			x++;
			if(x > 3) {
				x = 0;
				y++;
			}
			// ====== ======
			
			buttons[i].addActionListener(new ButtonActionListener());
			buttons[i].addMouseListener(me);
			
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
		setBackground(Color.DARK_GRAY);
		setLocationRelativeTo(null); // 화면 가운데에 띄우기
		setResizable(false); // 사이즈조절 불가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을때 실행중인 프로그램 도 종료
		
	}

	public void makeFrame(JButton c, int x , int y, int w, int h) {
		gbc.gridy = y;
		gbc.gridx = x;
		gbc.gridheight = h;
		gbc.gridwidth = w;
		grid.setConstraints(c, gbc);
	}
	
	// 연산자 중복입력 불가.
	class ButtonActionListener implements ActionListener{
		// ActionListener가 가지고 있는 메서드를 override
		// 버튼 이벤트를 처리해준다.
		@Override
		public void actionPerformed(ActionEvent e) { // 이벤트 처리!
//			System.out.println(e.getActionCommand());
			String inputValue = e.getActionCommand(); // 어떤 버튼이 눌렸나.
//			int target = buttonString.indexOf(operation); // 버튼 index
//			int befo = buttonString.indexOf(prev_operation); // 전에 눌린 버튼이요
//			Interval
//			int index = Arrays.binarySearch(button_names, operation);
//			System.out.println(buttons[target].getText()); // 잘나오고요
//			buttons[befo].setBorder(tB);
//			buttons[befo].setBackground(Color.white);
//			buttons[target].setBorder(new LineBorder(Color.red));
			
			// 취소키면 => 초기화
			if(inputValue.equals("C")) { 
//				System.out.println("취소키");
				inputSpace.setText("");
			// 등호 => calculate함수에서 계산한 결과를 double형으로 반환해주고, inputSpace에 뿌려주고, num 초기화
			}else if( inputValue.equals("=")) {
//				System.out.println("등호키");
//				calculator 함수로 가서 계산 하기
				String result = Double.toString(calculate(inputSpace.getText()));
				inputSpace.setText(""+result);
				num = "";
			}else if(inputValue.equals("±")) {
//				System.out.println("음수플마키");
				/*
				 *   플러스 마이너스 버튼이다.
				 *   숫자면 -1을 곱해주고
				 *   사칙연산 키가 오면 계산이 된다.
				 *   하지만 플러스마이너스 키를 누른 다음에 또 숫자가 나오면 inputSpace가 초기화 된다.(맥북에서)
				 *   맥북은 사칙연산버튼을 누르면 inputSpace에 값이 바로바로 사라진다.
				 *   근데 현재 내 계산기는 모든 계산식이 inputSpace에서 나온다
				 *   따라서  플러스 마이너스 키를 쓰기 애매하다.
				 *   플러스마이너스 키는 나중에 구현하고
				 *   사칙연산 우선순위를 먼저 구현해보자.
				 *   마찬가지로 마이너스 처리도 추후에 맥북처럼 바로바로 값을 초기화 시키거나
				 *   괄호를 넣는 방법으로 처리해야겠다.
				 */
				// 플러스 마이너스버튼
				// 숫자면 -로 나오게한다.
				// 다음에 또 숫자가 나오면 초기화 된다.
				// 사칙연산을 누르면 계산이 된다.
				// 맥북 계산기처럼 숫자 나오고 없어지고 하는 형식이면 우선순위 둘 필요 없음.
				// 근데 현재처럼모든 텍스트를 나오게 한다면 필요. -> 그러면 플마키를 쓸 수 없을것같음.
				// 플마키를 없애고 우선순위를 구현해 보는 것으로.
				// 그럼 마이너스는 어떠케 표현하죠? 띠롱때롱 하지마!
//				buttons[1].setBorder(new LineBorder(Color.red));
//				lastword = equation.get(equation.size());
				if(prev_operation.matches("[÷+x-]")  || prev_operation.matches("%") || inputSpace.getText().equals("") ) {
					
//					inputSpace.setText(e.getActionCommand());
				}
				else 	{
//					prev_operation = String.valueOf(Integer.valueOf(prev_operation) * (-1)) ;
				}

			// 사칙연산이면!?
			// 하나로 합칠 방법이 있을까?
			}else if(inputValue.matches("[÷+x-]") || inputValue.matches("%") ) {
		    	if(inputSpace.getText().equals("") ) {
//		    		System.out.println("빈칸인데 사칙연산이 들어왔따");
		    		inputSpace.setText("");
	    		}else if(prev_operation.matches("[÷+x-]")  || prev_operation.matches("%")  || prev_operation.matches("±")){
//		    		System.out.println("전에 사칙연산인데 또 사칙연산이 들어왔다.)");
		    		String is = inputSpace.getText();
		    		String lastS = is.substring(0, is.length() -1 );
		    		inputSpace.setText("");
		    		inputSpace.setText(lastS + inputValue);
		    		
		    	}else {
//		    		System.out.println(" 숫자다음에 사칙연산이 들어왔다..");
		    		inputSpace.setText(inputSpace.getText() + inputValue);
//		    		prev_operation = inputValue; // 얘 왜 여깄지?
		    	}
			// 숫자이면 => 연결연결
			} else {
//				System.out.println("숫자인경우");
				inputSpace.setText(inputSpace.getText() + inputValue);
			}
			
			// 사이즈...
//			System.out.println(inputSpace.getSize());
			// prev_operation에 현재 키 저장. => 다음 키와 비교위해
//			prev_operation = e.getActionCommand();
			// 아래처럼 해도 되지 않나?
			prev_operation = inputValue;
		
		}
	}
	
	// 연산기호 우선순위 됐고 그 다음은 inputText size 넘어가는거 폰트 크기 조절하기
	// % 는 나머지가 아니라 나누기100
	
	private void preprocess(String inputText) {
		numStack.clear();
		opStack.clear();
		
		for(int i=0; i<inputText.length(); i++) {
			char ch = inputText.charAt(i); // 2
			
			if(ch == '-' || ch == '+'  || ch == 'x' || ch== '÷' || ch== '%') {
				if(num != "" ) numStack.add(Double.valueOf(num));
				if(!opStack.isEmpty() && (opStack.peek().equals('x' ) || opStack.peek().equals('÷') || opStack.peek().equals('%' ))) {
					
					double n1 = numStack.pop();
					double n2 = numStack.pop();
					Character oper = opStack.pop();
					
					if(oper.equals('x')){
						numStack.add(n2*n1);
					}else if(oper.equals('%')){
						numStack.add(n2%n1);
					}else if(oper.equals('÷')) {
						numStack.add(n2/n1);
					}
				}

				opStack.add(ch);
				num ="";
			} else {
				num = num + ch;
			}
			
			if( i == inputText.length()-1) {
				if(ch == '-' || ch == '+'  || ch == 'x' || ch== '÷' || ch== '%') {
					opStack.pop();
				}			
				if(!opStack.isEmpty() && (opStack.peek().equals('x' ) || opStack.peek().equals('÷') || opStack.peek().equals('%' ))){
					double n1 = Double.valueOf(num);
					double n2 = numStack.pop();
					Character oper = opStack.pop();
					if(oper.equals('x')){
						numStack.add(n2*n1);
					}else if(oper.equals('%')){
						numStack.add(n2%n1);
					}else if(oper.equals('÷')) {
						numStack.add(n2/n1);
					}
				}else {
					if(num != "" ) numStack.add(Double.valueOf(num));
				}
			}
		}
	}
	
	// 1+2*3+2 이런식으로  String 이 들어오겠죠
	private double calculate(String inputText) {
		preprocess(inputText);
		
//		System.out.println("inputText = " + inputText);
//		System.out.println("numStack = " + numStack.size());
//		System.out.println("opStack = " + opStack.size());
//		for(Double i : numStack) {
//			System.out.println("num = " + i);
//		}
//		for(Character c : opStack) {
//			System.out.println("op = " + c);
//		}
		while(!opStack.isEmpty() && numStack.size() >= 2) {
			double n1 = numStack.pop();
			double n2 = numStack.pop();
			Character op = opStack.pop();
			
			if(op == '+') {
				numStack.add(n1+n2);
			}else if(op == '-'){
				numStack.add(n2-n1);
			}
		}
		return numStack.pop();
	}

	
	class MouseActionListener implements MouseListener{
		
//		String operation = e.getActionCommand(); // 어떤 버튼이 눌렸나.
//		int target = buttonString.indexOf(operation); // 버튼 index
//		int befo = buttonString.indexOf(prev_operation); // 전에 눌린 버튼이요
////		Interval
////		int index = Arrays.binarySearch(button_names, operation);
////		System.out.println(buttons[target].getText()); // 잘나오고요
//		buttons[befo].setBorder(tB);
//		buttons[befo].setBackground(Color.white);
//		buttons[target].setBorder(new LineBorder(Color.red));
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
//			JButton jb = (JButton)e.getSource();
//			System.out.println(" mouseClicked : "+jb.getText());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			JButton jb = (JButton)e.getSource();
			int target = buttonString.indexOf(jb.getText());
			buttons[target].setBorder(new LineBorder(Color.black));
			
			if(jb.getText().matches("[÷+=x-]")) {
				buttons[target].setBackground(Color.green);
			}else if(jb.getText().matches("[C±%]")) {
				buttons[target].setBackground(Color.green);
			}else {
				buttons[target].setBackground(Color.green);
			}
//			System.out.println(" mousePressed : "+e.getSource());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			JButton jb = (JButton)e.getSource();
			int target = buttonString.indexOf(jb.getText());
			buttons[target].setBorder(tB);
			// 버튼 색
			if(jb.getText().matches("[÷+=x-]")) {
				buttons[target].setBackground(new Color(255, 159, 9));
			}else if(jb.getText().matches("[C±%]")) {
				buttons[target].setBackground(new Color(97, 99, 102));
			}else {
				buttons[target].setBackground(new Color(123, 125, 127));
			}
//			System.out.println(" mouseReleased : "+e.getSource());
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	
	public static void main(String[] args) {
		new Calculator();
	}

}
