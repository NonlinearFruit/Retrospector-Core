
package retrospector.core.interactor;

import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Presenter;
import retrospector.core.boundry.Request;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Factoid;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableFactoid;

public class CrudFactoidUseCase implements Interactor{

    private CrudFactoidRequest request;
    private DataGateway dataGateway;
    private Presenter presenter;
    
    public CrudFactoidUseCase(DataGateway dataGateway, Presenter presenter) {
        this.dataGateway = dataGateway;
        this.presenter = presenter;
    }
    
    @Override
    public void execute(Request request) {
        this.request = (CrudFactoidRequest) request;
        switch(this.request.getCrud()){
            case Create:
                createFactoid();
                break;
            default:
            case Read:
                readFactoid();
                break;
            case Update:
                updateFactoid();
                break;
            case Delete:
                deleteFactoid();
                break;
        }
    }

    private void createFactoid() {
        Factoid factoid = dataGateway.addFactoid(convert(request.getFactoid()));
        presenter.factoidAdded(convert(factoid));
    }

    private void readFactoid() {
        Factoid factoid = dataGateway.getFactoid(request.getFactoidId());
        presenter.factoidRetrieved(convert(factoid));
    }

    private void updateFactoid() {
        RequestableFactoid requestable = request.getFactoid();
        Factoid factoid = dataGateway.updateFactoid(convert(requestable));
        presenter.factoidUpdated(convert(factoid));
    }

    private void deleteFactoid() {
        int factoidId = request.getFactoidId();
        dataGateway.deleteFactoid(request.getFactoidId());
        presenter.factoidDeleted(factoidId);
    }
    
    private Factoid convert(RequestableFactoid factoid) {
        return EntityConverter.convert(factoid);
    }
    
    private RequestableFactoid convert(Factoid factoid) {
        return EntityConverter.convert(factoid);
    }
}
