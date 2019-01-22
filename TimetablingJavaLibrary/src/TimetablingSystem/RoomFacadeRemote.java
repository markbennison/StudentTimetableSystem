/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.util.List;
import javax.ejb.Remote;
import utils.RoomDetails;

/**
 *
 * @author markb
 */
@Remote
public interface RoomFacadeRemote {

	public void createRoom(RoomDetails details);

	public void editRoom(RoomDetails details);

	public void removeRoom(Integer id);

	public RoomDetails find(Object pk);

	public List<RoomDetails> findAll();
	
}
