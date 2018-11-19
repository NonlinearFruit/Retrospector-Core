
package retrospector.core.interactor;

import java.util.List;
import java.util.stream.Collectors;
import retrospector.core.boundry.CrudPresenter;
import retrospector.core.entity.Media;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableMedia;
import retrospector.core.datagateway.CrudDataGateway;

public class CrudMediaUseCase extends CrudUseCase<CrudMediaRequest>{

  private final CrudPresenter<RequestableMedia> presenter;
  private final CrudDataGateway<Media> mediaGateway;

  public CrudMediaUseCase(CrudDataGateway<Media> dataGateway, CrudPresenter<RequestableMedia> presenter) {
    this.mediaGateway = dataGateway;
    this.presenter = presenter;
  }

  @Override
  public void create() {
    Media media = mediaGateway.add(convert(request.getRequestable()));
    presenter.added(convert(media));
  }
  
  @Override
  public void read() {
    Media media = mediaGateway.get(request.getRequestableId());
    presenter.retrieved(convert(media));
  }

  @Override
  public void update() {
    RequestableMedia requestable = request.getRequestable();
    Media media = mediaGateway.update(convert(requestable));
    presenter.updated(convert(media));
  }

  @Override
  public void delete() {
    int mediaId = request.getRequestableId();
    mediaGateway.delete(request.getRequestableId());
    presenter.deleted(mediaId);
  }

  @Override
  protected void readAll() {
    List<Media> media = mediaGateway.getAll();
    presenter.retrievedAll(
      media.stream()
        .map(this::convert)
        .collect(Collectors.toList())
    );
  }

  @Override
  protected void readAllById() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  private Media convert(RequestableMedia media) {
    return EntityConverter.convert(media);
  }
  
  private RequestableMedia convert(Media media) {
    return EntityConverter.convert(media);
  }
}
