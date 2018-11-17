
package retrospector.core.boundry;

import retrospector.core.request.model.RequestableFactoid;

public interface FactoidPresenter {
    public void factoidAdded(RequestableFactoid factoid);
    public void factoidRetrieved(RequestableFactoid factoid);
    public void factoidUpdated(RequestableFactoid factoid);
    public void factoidDeleted(int factoidId);
}
