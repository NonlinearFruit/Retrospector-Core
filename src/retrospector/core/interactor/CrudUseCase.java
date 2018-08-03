
package retrospector.core.interactor;

import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Presenter;
import retrospector.core.boundry.Request;
import retrospector.core.datagateway.DataGateway;

public abstract class CrudUseCase<T extends CrudRequest> implements Interactor{

    protected T request;
    protected DataGateway dataGateway;
    protected Presenter presenter;
    
    public CrudUseCase(DataGateway dataGateway, Presenter presenter) {
        this.dataGateway = dataGateway;
        this.presenter = presenter;
    }
    
    @Override
    public void execute(Request request) {
        this.request = (T) request;
        switch(this.request.getCrud()){
            case Create:
                create();
                break;
            default:
            case Read:
                read();
                break;
            case Update:
                update();
                break;
            case Delete:
                delete();
                break;
            case ReadAll:
                readAll();
                break;
            case ReadAllById:
                readAllById();
                break;
        }
    }

    protected abstract void create();

    protected abstract  void read();

    protected abstract  void update();

    protected abstract  void delete();
    
    protected abstract  void readAll();
    
    protected abstract  void readAllById();
}
