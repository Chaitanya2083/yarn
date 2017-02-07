package org.grayleaves.sweater;

public class KnitStatusResponse extends StatusResponse {


	protected static final int STITCHES_PER_YARD = 200;
	private static KnitStatusResponseEnum COLOR = KnitStatusResponseEnum.GRAY;

	public static void color(KnitStatusResponseEnum color) {
		COLOR  = color; 
	}

	private String knitResponse = "";
	private YarnService yarnService;

	public KnitStatusResponse() {
		super(); 
	}
	

	public KnitStatusResponse(YarnService yarnService) {
		this(); 
		this.yarnService = yarnService; 
	}


	public void knit(int stitches) {
		int yards = (stitches / STITCHES_PER_YARD) + 1 ;
		int available = getYarn(COLOR, yards);
		int possible = getPossibleStitches(stitches, available, yards);
		if (available < yards) {
			
			knitResponse = "insufficient "+getColor()+" yarn available to knit "+stitches+" stitches; "+possible+" stitches knitted";
		}
		else {
			knitResponse = stitches+" stitches knitted using "+available+" yards of GRAY yarn";
		}
	}

	private int getPossibleStitches(int stitches, int available, int yards) {
		int possible = stitches;
		if (available < yards) {
			possible = available * STITCHES_PER_YARD;
		}
		return possible;
	}
	
	protected int getYarn(KnitStatusResponseEnum color, int yards) {
		int available = 0; 
		if (yarnService != null) {
			available = yarnService.getYarn(color, yards); 
		}
		return available;
	}

	public String getColor() {
		return COLOR.toString();
	}
	public void setColor(String color) {
		
	}
	public String getKnitResponse() {
		return knitResponse;
	}


	public void setKnitResponse(String knitResponse) {
		this.knitResponse = knitResponse;
	}
}