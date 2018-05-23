package projectII.builder;

import projectII.ServiceLocator;
import projectII.TrafficNode;
import projectII.TurningRoad;
import projectII.Vector;

public class TurningBuilder {
	private final ServiceLocator locator;
	private Rectangle rec;
	private TrafficNode mid, top, bot, left, right;

	public TurningBuilder(ServiceLocator locator) {
		this.locator = locator;
	}
	
	public void setNodes(
			TrafficNode mid,
			TrafficNode top,
			TrafficNode bot,
			TrafficNode left,
			TrafficNode right)
	{
		this.mid = mid;
		this.top = top;
		this.bot = bot;
		this.left = left;
		this.right = right;
	}
	
	public void setRectangle(Rectangle rec) {
		this.rec = rec;
	}
	
	public void build() {
		for (int x = rec.getX(); x < rec.getEndX(); x++) 
			for (int y = rec.getY(); y < rec.getEndY(); y++)
				new TurningRoad(locator, new Vector(x, y));
		
		SingleTurningBuilder singleBuilder = new SingleTurningBuilder(locator);
		singleBuilder.setRectangle(rec);

		singleBuilder.setNodes(mid, top, bot, left, right);
		DirectionCalculator upCal = new UpDirectionCalculator();
		singleBuilder.setDirectionCalculator(upCal);
		singleBuilder.build();
	}
}
