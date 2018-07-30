
package retrospector.core.interactor;

import retrospector.core.boundry.Presenter;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Media;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableMedia;

public class CrudMediaUseCase extends CrudUseCase<CrudMediaRequest>{

    public CrudMediaUseCase(DataGateway dataGateway, Presenter presenter) {
        super(dataGateway, presenter);
    }

    @Override
    public void create() {
        Media media = dataGateway.addMedia(convert(request.getRequestable()));
        presenter.mediaAdded(convert(media));
    }
    
    @Override
    public void read() {
        Media media = dataGateway.getMedia(request.getRequestableId());
        presenter.mediaRetrieved(convert(media));
    }

    @Override
    public void update() {
        RequestableMedia requestable = request.getRequestable();
        Media media = dataGateway.updateMedia(convert(requestable));
        presenter.mediaUpdated(convert(media));
    }

    @Override
    public void delete() {
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