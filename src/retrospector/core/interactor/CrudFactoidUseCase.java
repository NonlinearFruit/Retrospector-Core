
package retrospector.core.interactor;

import retrospector.core.boundry.CrudPresenter;
import retrospector.core.entity.Factoid;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableFactoid;
import retrospector.core.datagateway.CrudDataGateway;

public class CrudFactoidUseCase extends CrudUseCase<CrudFactoidRequest> {

  private final CrudDataGateway<Factoid> factoidGateway;
  private final CrudPresenter<RequestableFactoid> presenter;

    public CrudFactoidUseCase(CrudDataGateway<Factoid> dataGateway, CrudPresenter<RequestableFactoid> presenter) {
      this.factoidGateway = dataGateway;
      this.presenter = presenter;
    }

    @Override
    protected void create() {
        Factoid factoid = factoidGateway.add(convert(request.getRequestable()));
        presenter.added(convert(factoid));
    }

    @Override
    protected void read() {
        Factoid factoid = factoidGateway.get(request.getRequestableId());
        presenter.retrieved(convert(factoid));
    }

    @Override
    protected void update() {
        RequestableFactoid requestable = request.getRequestable();
        Factoid factoid = factoidGateway.update(convert(requestable));
        presenter.updated(convert(factoid));
    }

    @Override
    protected void delete() {
        int factoidId = request.getRequestableId();
        factoidGateway.delete(factoidId);
        presenter.deleted(factoidId);
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
