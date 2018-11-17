
package retrospector.core.interactor;

import java.util.List;
import java.util.stream.Collectors;
import retrospector.core.boundry.CrudPresenter;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Media;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableMedia;

public class CrudMediaUseCase extends CrudUseCase<CrudMediaRequest>{

  private final CrudPresenter<RequestableMedia> presenter;
  private final DataGateway dataGateway;

    public CrudMediaUseCase(DataGateway dataGateway, CrudPresenter<RequestableMedia> presenter) {
      this.dataGateway = dataGateway;
      this.presenter = presenter;
    }

    @Override
    public void create() {
        Media media = dataGateway.addMedia(convert(request.getRequestable()));
        presenter.added(convert(media));
    }
    
    @Override
    public void read() {
        Media media = dataGateway.getMedia(request.getRequestableId());
        presenter.retrieved(convert(media));
    }

    @Override
    public void update() {
        RequestableMedia requestable = request.getRequestable();
        Media media = dataGateway.updateMedia(convert(requestable));
        presenter.updated(convert(media));
    }

    @Override
    public void delete() {
        int mediaId = request.getRequestableId();
        dataGateway.deleteMedia(request.getRequestableId());
        presenter.deleted(mediaId);
    }

    @Override
    protected void readAll() {
        List<Media> media = dataGateway.getMedia();
        presenter.retrievedAll(
                media.stream()
                        .map(this::convert)
                        .collect(Collectors.toList())
        );
    }

    @Override
    protected void readAllById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Media convert(RequestableMedia media) {
        return EntityConverter.convert(media);
    }
    
    private RequestableMedia convert(Media media) {
        return EntityConverter.convert(media);
    }
}
