
package retrospector.core.interactor;

import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Presenter;
import retrospector.core.boundry.Request;
import retrospector.core.datagateway.DataGateway;

public class DeleteMediaUseCase implements Interactor{

    private DataGateway dataGateway;
    private Presenter presenter;
    
    public DeleteMediaUseCase(DataGateway dataGateway, Presenter presenter) {
        this.dataGateway = dataGateway;
        this.presenter = presenter;
    }
    
    @Override
    public void execute(Request request) {
        int mediaId = ((DeleteMediaRequest) request).getMediaId();
        dataGateway.deleteMedia(mediaId);
        presenter.mediaDeleted(mediaId);
    }
    
}
