
package retrospector.core.interactor;

import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Presenter;
import retrospector.core.boundry.Request;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Media;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableMedia;

public class CrudMediaUseCase implements Interactor{

    private CrudMediaRequest request;
    private DataGateway dataGateway;
    private Presenter presenter;
    
    public CrudMediaUseCase(DataGateway dataGateway, Presenter presenter) {
        this.dataGateway = dataGateway;
        this.presenter = presenter;
    }
    
    @Override
    public void execute(Request request) {
        this.request = (CrudMediaRequest) request;
        switch(this.request.getCrud()){
            case Create:
                createMedia();
                break;
            default:
            case Read:
                readMedia();
                break;
            case Update:
                updateMedia();
                break;
            case Delete:
                deleteMedia();
                break;
        }
    }

    private void createMedia() {
        Media media = dataGateway.addMedia(convert(request.getRequestable()));
        presenter.mediaAdded(convert(media));
    }

    private void readMedia() {
        Media media = dataGateway.getMedia(request.getRequestableId());
        presenter.mediaRetrieved(convert(media));
    }

    private void updateMedia() {
        RequestableMedia requestable = request.getRequestable();
        Media media = dataGateway.updateMedia(convert(requestable));
        presenter.mediaUpdated(convert(media));
    }

    private void deleteMedia() {
        int mediaId = request.getRequestableId();
        dataGateway.deleteMedia(request.getRequestableId());
        presenter.mediaDeleted(mediaId);
    }
    
    private Media convert(RequestableMedia media) {
        return EntityConverter.convert(media);
    }
    
    private RequestableMedia convert(Media media) {
        return EntityConverter.convert(media);
    }
}
