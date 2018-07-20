/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrospector.core.boundry;

import retrospector.core.request.model.RequestableFactoid;
import retrospector.core.request.model.RequestableMedia;

/**
 *
 * @author root
 */
public interface Presenter {
    public void mediaAdded(RequestableMedia media);
    public void mediaDeleted(int mediaId);
    
    public void factoidAdded(RequestableFactoid factoid);
    public void factoidRetrieved(RequestableFactoid factoid);
    public void factoidUpdated(RequestableFactoid factoid);
    public void factoidDeleted(int factoidId);
}
