import javax.ws.rs.core.GenericEntity;

import domain.Event;
import domain.Inscription;
import domain.InscriptionId;
import domain.Room;
import junit.framework.TestCase;
import model.InscriptionModel;
import model.RoomModel;

public class Test extends TestCase{

	public void testRoomCreation() throws Exception {
		
		Room room = new Room ((short)0,"10m","office",(short)50, null);
		RoomModel rm = new RoomModel();
		short idRoom = rm.save(room);
		Room rget=rm.get(idRoom);
		assertEquals(idRoom,rget.getRoomId());
		assertTrue("10m".compareTo(rget.getRoomArea()) == 0);
		assertTrue("office".compareTo(rget.getRoomType()) == 0);
		assertEquals((short)50,rget.getCapacity());
	}
	
	public void testDeleteInscription() throws Exception {
		
		InscriptionId id = new InscriptionId((short)1, (short)4);
		InscriptionModel im = new InscriptionModel();
		Inscription i = im.get(id);
		//System.out.println(i.getUser().getUserFirstname());
		InscriptionId userid = i.getId();
		//assertEquals((short)2,userid.getMemberId());
		im.delete(im.get(id));
		assertEquals((short)1,i.getUser().getUserId());
	}
}
