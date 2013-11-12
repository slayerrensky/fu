package fu.commonWords;

import haufigkeit.Bigramme;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class ColoredFrame extends JFrame {
	/** Creates a new instance of MyWindow */

	public ColoredFrame(String decryptText, ArrayList<Bigramme> wordList) {
		JTextArea textArea = new JTextArea(5, 20);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		char[] charArray = decryptText.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (i % 66 == 0)
				textArea.setText(textArea.getText() + "\n");
			textArea.setText(textArea.getText() + charArray[i]);
		}
		Highlighter highlighter = textArea.getHighlighter();
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		for (Bigramme cw : wordList) {
			if (decryptText.contains(cw.letters)) {
				int index = 0;
				while (true) {
					index = decryptText.indexOf(cw.letters, index);
					if (index != -1) {
						try {
							highlighter.addHighlight(index, index+2, DefaultHighlighter.DefaultPainter);
						} catch (BadLocationException e) {
							e.printStackTrace();
						}
						break;
					} else
						break;
				}
			}
		}
		this.setSize(500, 500);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.add(textArea);
		// this.pack();
		this.setVisible(true);
	}
}
