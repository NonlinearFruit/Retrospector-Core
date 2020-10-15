using Retrospector.Core.Boundary;
using Retrospector.Core.Crud;
using Retrospector.Core.Crud.Models;
using Retrospector.Core.Tests.TestDoubles.Crud;
using Retrospector.Core.Tests.Tests.Crud.Models;
using Retrospector.Core.Tests.Utilities;
using Xunit;

namespace Retrospector.Core.Tests.Tests.Crud
{
    public class CrudUseCaseTests
    {
        private IUseCase<CrudRequest<Model_TestDouble>> _useCase;
        private CrudPresenter_TestDouble<Model_TestDouble> _presenter;
        private CrudDataGateway_TestDouble<Model_TestDouble> _dataGateway;

        public CrudUseCaseTests()
        {
            _presenter = new CrudPresenter_TestDouble<Model_TestDouble>();
            _dataGateway = new CrudDataGateway_TestDouble<Model_TestDouble>();
            _useCase = new CrudUseCase<Model_TestDouble>(_presenter, _dataGateway);
        }

        [Fact]
        public void create_request_calls_data_gateway()
        {
            var model = new Model_TestDouble();

            _useCase.Execute(new CrudRequest<Model_TestDouble>
            {
                Crud = CrudEnum.Create,
                Model = model
            });

            Assert.Equal(Verify.Once, _dataGateway.CountOf_Add_Calls);
            Assert.Equal(model, _dataGateway.LastModelPassedTo_Add);
        }

        [Fact]
        public void create_request_informs_presenter()
        {
            var model = new Model_TestDouble();
            _dataGateway.ReturnFor_Add = model;

            _useCase.Execute(new CrudRequest<Model_TestDouble>
            {
                Crud = CrudEnum.Create,
            });

            Assert.Equal(Verify.Once, _presenter.CountOf_Added_Calls);
            Assert.Equal(model, _presenter.LastModelPassedTo_Added);
        }
    }
}