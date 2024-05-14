import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.util.regex.*;
import java.awt.Color;
import java.awt.Font;

public class Calc extends JFrame {
	private String exp="";
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtField;
	private JTextField txtStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calc frame = new Calc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	public class Infix2Postfix {
	    public static String convert(String exp) {
	        if (exp == null || exp.length() == 0) return null;
	        // 숫자와 연산자를 정확히 구분하기 위한 정규식 패턴
	        Pattern pattern = Pattern.compile("(-?\\d+\\.?\\d*)|([+\\-*/()])");
	        Matcher matcher = pattern.matcher(exp);
	        Stack<String> stack = new Stack<>();
	        StringBuilder output = new StringBuilder();

	        while (matcher.find()) {
	            String tok = matcher.group();

	            if (tok.matches("-?\\d+\\.?\\d*")) {  // 숫자에 대한 처리
	                output.append(tok).append(' ');
	            } else if ("(".equals(tok)) {
	                stack.push(tok);
	            } else if (")".equals(tok)) {
	                while (!stack.isEmpty() && !"(".equals(stack.peek())) {
	                    output.append(stack.pop()).append(' ');
	                }
	                if (!stack.isEmpty() && "(".equals(stack.peek())) {
	                    stack.pop();
	                }
	            } else {  // 연산자에 대한 처리
	                while (!stack.isEmpty() && getPriority(tok) <= getPriority(stack.peek())) {
	                    output.append(stack.pop()).append(' ');
	                }
	                stack.push(tok);
	            }
	        }

	        while (!stack.isEmpty()) {
	            output.append(stack.pop()).append(' ');
	        }

	        return output.toString().trim();
	    }

	    private static int getPriority(String op) {
	        switch (op) {
	            case "+":
	            case "-":
	                return 1;
	            case "*":
	            case "/":
	                return 2;
	            default:
	                return -1;
	        }
	    }

	    public static double eval(String postfix) {
	        Stack<Double> stack = new Stack<>();
	        StringTokenizer tokenizer = new StringTokenizer(postfix, " ");
	        while (tokenizer.hasMoreTokens()) {
	            String token = tokenizer.nextToken();
	            if (token.matches("-?\\d+\\.?\\d*")) {  // 숫자 확인
	                stack.push(Double.parseDouble(token));
	            } else {
	                double b = stack.pop();
	                double a = stack.pop();
	                switch (token) {
	                    case "+":
	                        stack.push(a + b);
	                        break;
	                    case "-":
	                        stack.push(a - b);
	                        break;
	                    case "*":
	                        stack.push(a * b);
	                        break;
	                    case "/":
	                        stack.push(a / b);
	                        break;
	                }
	            }
	        }
	        return stack.pop();
	    }
	}

	/**
	 * Create the frame.
	 */
	public Calc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		txtField = new JTextField();
		txtField.setFont(new Font("굴림", Font.BOLD, 46));
		txtField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtField.setHorizontalAlignment(SwingConstants.RIGHT);
		txtField.setEditable(false);
		txtField.setText("0");
		panel.add(txtField, BorderLayout.NORTH);
		txtField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setText("Status");
		panel_1.add(txtStatus);
		txtStatus.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(255, 255, 255));
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(4, 4, 10, 5));
		
		JButton btnNewButton = new JButton("7");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"7";
				txtField.setText(exp);
			}
		});
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("8");
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"8";
				txtField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("9");
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"9";
				txtField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("/");
		btnNewButton_4.setForeground(new Color(0, 0, 255));
		btnNewButton_4.setFont(new Font("굴림", Font.ITALIC, 27));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"/";
				txtField.setText(exp);
			}
		});
		
		JButton btnNewButton_6 = new JButton("X");
		btnNewButton_6.setForeground(new Color(0, 0, 255));
		btnNewButton_6.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_6.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        exp = exp + "*";  // "X" 대신 "*" 사용
		        txtField.setText(exp);
		    }
		});
		panel_2.add(btnNewButton_6);
		panel_2.add(btnNewButton_4);
		
		JButton btnNewButton_7 = new JButton("6");
		btnNewButton_7.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"6";
				txtField.setText(exp);
			}
		});
		
		JButton btnNewButton_3 = new JButton("5");
		btnNewButton_3.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"5";
				txtField.setText(exp);
			}
		});
		
		JButton btnNewButton_5 = new JButton("4");
		btnNewButton_5.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"4";
				txtField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_5);
		panel_2.add(btnNewButton_3);
		panel_2.add(btnNewButton_7);
		
		JButton btnNewButton_16 = new JButton("+");
		btnNewButton_16.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_16.setForeground(new Color(0, 0, 255));
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"+";
				txtField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_16);
		
		
		JButton btnNewButton_11 = new JButton("-");
		btnNewButton_11.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_11.setForeground(new Color(0, 0, 255));
		btnNewButton_11.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (exp.isEmpty() || "+-*/(".contains(exp.substring(exp.length() - 1))) {
		            // 음수 부호로 처리 (연산자 뒤나 식이 비어있을 경우)
		            exp += "-";
		        } else {
		            // 뺄셈 연산자로 처리
		            exp += " -";
		        }
		        txtField.setText(exp);
		    }
		});
		panel_2.add(btnNewButton_11);
		
				
		JButton btnNewButton_8 = new JButton("1");
		btnNewButton_8.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"1";
				txtField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("2");
		btnNewButton_9.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_9.setForeground(new Color(0, 0, 0));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"2";
				txtField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("3");
		btnNewButton_10.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"3";
				txtField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_10);
														
														
														
																
		JButton btnNewButton_13 = new JButton("C");
		btnNewButton_13.setFont(new Font("굴림", Font.BOLD, 30));
		btnNewButton_13.setBackground(new Color(255, 255, 255));
		btnNewButton_13.setForeground(new Color(255, 0, 0));
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = "";  // 식을 저장하는 문자열 초기화
				txtField.setText("0");  // 텍스트 필드를 0으로 설정
				}
			});
		panel_2.add(btnNewButton_13);
														
																
		JButton btnNewButton_15 = new JButton("=");
		btnNewButton_15.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String postfix = Infix2Postfix.convert(exp);
				double value=Infix2Postfix.eval(postfix);
				txtField.setText(String.valueOf(value));
				}
			});
		panel_2.add(btnNewButton_15);
														
		JButton btnNewButton_12 = new JButton("0");
		btnNewButton_12.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp+"0";
				txtField.setText(exp);
			}
														
		});
														
		panel_2.add(btnNewButton_12);
												
												
		JButton btnNewButton_14 = new JButton("(");										
		btnNewButton_14.setFont(new Font("굴림", Font.BOLD, 27));										
		btnNewButton_14.addActionListener(new ActionListener() {									    
			public void actionPerformed(ActionEvent e) {
				exp += "(";  // 현재 식에 '(' 추가								        
				txtField.setText(exp);  // 텍스트 필드 업데이트									    
			}
		});
		
		panel_2.add(btnNewButton_14);
		
		JButton btnNewButton_17 = new JButton(")");
		btnNewButton_17.setFont(new Font("굴림", Font.BOLD, 27));
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp += ")";  // 현재 식에 ')' 추가
				txtField.setText(exp);  // 텍스트 필드 업데이트									
			}
		});
		panel_2.add(btnNewButton_17);

	}

	public JTextField getTxtSadsa() {
		return txtField;
	}
}