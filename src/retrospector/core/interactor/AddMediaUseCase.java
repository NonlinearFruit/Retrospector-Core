
package retrospector.core.interactor;

import retrospector.core.boundry.Request;
import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Presenter;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Media;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableMedia;

public class AddMediaUseCase implements Interactor{
    
    private DataGateway dataGateway;
    private Presenter presenter;
    
    public AddMediaUseCase(DataGateway dataGateway, Presenter presenter) {
        this.dataGateway = dataGateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request genericRequest) {
        AddMediaRequest request = (AddMediaRequest) genericRequest;
        Media media = getMedia(request);
        Media storedMedia = dataGateway.addMedia(media);
        RequestableMedia requestableMedia = getRequestableMedia(storedMedia);
        presenter.mediaAdded(requestableMedia);
    }
    
    private Media getMedia(AddMediaRequest request) {
        return EntityConverter.convert(request.getMedia());
    }
    
    private RequestableMedia getRequestableMedia(Media media) {
        return EntityConverter.convert(media);
    }
    
}
