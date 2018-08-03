
package retrospector.core.interactor;

import retrospector.core.boundry.Presenter;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Factoid;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableFactoid;

public class CrudFactoidUseCase extends CrudUseCase<CrudFactoidRequest> {

    public CrudFactoidUseCase(DataGateway dataGateway, Presenter presenter) {
        super(dataGateway, presenter);
    }

    @Override
    protected void create() {
        Factoid factoid = dataGateway.addFactoid(convert(request.getRequestable()));
        presenter.factoidAdded(convert(factoid));
    }

    @Override
    protected void read() {
        Factoid factoid = dataGateway.getFactoid(request.getRequestableId());
        presenter.factoidRetrieved(convert(factoid));
    }

    @Override
    protected void update() {
        RequestableFactoid requestable = request.getRequestable();
        Factoid factoid = dataGateway.updateFactoid(convert(requestable));
        presenter.factoidUpdated(convert(factoid));
    }

    @Override
    protected void delete() {
        int factoidId = request.getRequestableId();
        dataGateway.deleteFactoid(factoidId);
        presenter.factoidDeleted(factoidId);
    }
    
    private Factoid convert(RequestableFactoid factoid) {
        return EntityConverter.convert(factoid);
    }
    
    private RequestableFactoid convert(Factoid factoid) {
        return EntityConverter.convert(factoid);
    }

    @Override
    protected void readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void readAllById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
