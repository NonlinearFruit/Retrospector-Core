
package retrospector.core.interactor;

import retrospector.core.boundry.FactoidPresenter;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Factoid;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableFactoid;

public class CrudFactoidUseCase extends CrudUseCase<CrudFactoidRequest> {

  private final DataGateway dataGateway;
  private final FactoidPresenter presenter;

    public CrudFactoidUseCase(DataGateway dataGateway, FactoidPresenter presenter) {
      this.dataGateway = dataGateway;
      this.presenter = presenter;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void readAllById() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
