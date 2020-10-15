using Retrospector.Core.Boundary;
using Retrospector.Core.Crud.Interfaces;
using Retrospector.Core.Crud.Models;

namespace Retrospector.Core.Crud
{
    public class CrudUseCase<T> : IUseCase<CrudRequest<T>> where T : IModel
    {
        private readonly ICrudPresenter<T> _presenter;
        private readonly ICrudDataGateway<T> _dataGateway;

        public CrudUseCase(ICrudPresenter<T> presenter, ICrudDataGateway<T> dataGateway)
        {
            _presenter = presenter;
            _dataGateway = dataGateway;
        }

        public void Execute(CrudRequest<T> request)
        {
            _presenter.Added(_dataGateway.Add(request.Model));
        }
    }
}