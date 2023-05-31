package GUI;

import java.awt.Font;

public class InitGUI {
	private int SCREEN_HEIGHT = 700;
	private int SCREEN_WIDTH = 1400;
	private int COMPONENTS_HEIGHT = 40;
	private int BUTTON_HEIGHT = 40;
	private int BUTTON_WIDTH = 120;
	private int SPACE = 10;
	private String FONT_TYPE = "Tohoma";
	private int FONT = Font.PLAIN;
	private int FONT_SIZE = 16;
	public InitGUI() {
		
	}
	
	public int getSCREEN_HEIGHT() {
		return SCREEN_HEIGHT;
	}
	public void setSCREEN_HEIGHT(int sCREEN_HEIGHT) {
		SCREEN_HEIGHT = sCREEN_HEIGHT;
	}
	public int getSCREEN_WIDTH() {
		return SCREEN_WIDTH;
	}
	public void setSCCREEN_WIDTH(int sCREEN_WIDTH) {
		SCREEN_WIDTH = sCREEN_WIDTH;
	}
	public int getCOMPONENTS_HEIGHT() {
		return COMPONENTS_HEIGHT;
	}
	public void setCOMPONENTS_HEIGHT(int cOMPONENTS_HEIGHT) {
		COMPONENTS_HEIGHT = cOMPONENTS_HEIGHT;
	}
	public int getBUTTON_HEIGHT() {
		return BUTTON_HEIGHT;
	}
	public void setBUTTON_HEIGHT(int bUTTON_HEIGHT) {
		BUTTON_HEIGHT = bUTTON_HEIGHT;
	}
	public int getBUTTON_WIDTH() {
		return BUTTON_WIDTH;
	}
	public void setBUTTON_WIDTH(int bUTTON_WIDTH) {
		BUTTON_WIDTH = bUTTON_WIDTH;
	}
	public int getSPACE() {
		return SPACE;
	}
	public void setSPACE(int sPACE) {
		SPACE = sPACE;
	}
	public String getFONT_TYPE() {
		return FONT_TYPE;
	}
	public void setFONT_TYPE(String fONT_TYPE) {
		FONT_TYPE = fONT_TYPE;
	}
	public int getFONT() {
		return FONT;
	}
	public void setFONT(int fONT) {
		FONT = fONT;
	}
	public int getFONT_SIZE() {
		return FONT_SIZE;
	}
	public void setFONT_SIZE(int fONT_SIZE) {
		FONT_SIZE = fONT_SIZE;
	}
}
