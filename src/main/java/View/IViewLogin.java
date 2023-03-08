package View;

import javax.swing.*;

public interface IViewLogin {
	public String getUsername();
    public String getPassword();
    public JComboBox getComboBox();
    public void setSuccessMessageLabel(String text);
    public void setFailMessageLabel(String text);
}
