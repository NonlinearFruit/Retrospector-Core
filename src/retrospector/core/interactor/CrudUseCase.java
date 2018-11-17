
package retrospector.core.interactor;

import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Request;

public abstract class CrudUseCase<T extends CrudRequest> implements Interactor{

    protected T request;
    
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
