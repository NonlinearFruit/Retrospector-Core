using Retrospector.Core.Boundary;
using Retrospector.Core.Crud.Models;

namespace Retrospector.Core.Crud
{
    public class CrudRequestRouter : IRequestRouter
    {
        private readonly IUseCase<CrudRequest<Media>> _mediaUse;
        private readonly IUseCase<CrudRequest<Review>> _reviewUse;
        private readonly IUseCase<CrudRequest<Factoid>> _factoidUse;

        public CrudRequestRouter(IUseCase<CrudRequest<Media>> mediaUse, IUseCase<CrudRequest<Review>> reviewUse, IUseCase<CrudRequest<Factoid>> factoidUse)
        {
            _mediaUse = mediaUse;
            _reviewUse = reviewUse;
            _factoidUse = factoidUse;
        }

        public void Disseminate(IRequest request)
        {
            switch (request)
            {
                case CrudRequest<Media> mediaRequest:
                    _mediaUse.Execute(mediaRequest);
                    break;
                case CrudRequest<Review> reviewRequest:
                    _reviewUse.Execute(reviewRequest);
                    break;
                case CrudRequest<Factoid> factoidRequest:
                    _factoidUse.Execute(factoidRequest);
                    break;
            }
        }
    }
}