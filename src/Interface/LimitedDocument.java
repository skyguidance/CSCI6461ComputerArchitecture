package Interface;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class LimitedDocument extends PlainDocument {

    private int _maxLength = -1;
    private String _allowCharAsString = null;

    public LimitedDocument() {
        super();
    }

    public LimitedDocument(int maxLength) {
        super();
        this._maxLength = maxLength;
    }

    public void insertString(int offset, String str, AttributeSet attrSet) throws BadLocationException {
        if (str == null) {
            return;
        }
        if (_allowCharAsString != null && str.length() == 1) {
            if (_allowCharAsString.indexOf(str) == -1) {
                return;
            }
        }
        char[] charVal = str.toCharArray();
        String strOldValue = getText(0, getLength());
        byte[] tmp = strOldValue.getBytes();
        if (_maxLength != -1 && (tmp.length + charVal.length > _maxLength)) {
            return;
        }
        super.insertString(offset, str, attrSet);
    }

    public void setAllowChar(String str) {
        _allowCharAsString = str;
    }
}
