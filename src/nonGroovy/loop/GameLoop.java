package nonGroovy.loop;

public class GameLoop {

	public static final int UPS = 60;
	public static final long NANO_PER_SECOND = 1000 * 1000 * 1000;
	public static final long NANO_PER_UPDATE = NANO_PER_SECOND / UPS;
	
	private GameStateManager loop;
	
	public GameLoop() {
		loop = new GameStateManager();
		loop.pushState(new Game(loop));
		loop();
	}
	
	private void loop(){
		
		int fpsCounter = 0;
		int upsCounter = 0;
		
		long tick = System.nanoTime();
		
		long tock = tick + NANO_PER_UPDATE;
		long tockSecond = tick + NANO_PER_SECOND;
		
		while(loop.isLooping()){
			
			tick = System.nanoTime();
			
			if (tick > tock) {
				upsCounter++;
				loop.input();
				loop.update();
				tock += NANO_PER_UPDATE;
			}
			
			if (tick > tockSecond) {
				System.out.println("UPS: " + upsCounter);
				System.out.println("FPS: " + fpsCounter);
				upsCounter = 0;
				fpsCounter = 0;
				tockSecond += NANO_PER_SECOND;
			}
			
			
			fpsCounter++;
			loop.render();
		}
	}
	
	public static void main(String[] args){
		GameLoop game = new GameLoop();
	}
	
}
