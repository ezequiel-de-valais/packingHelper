angular.module('insumoApp.controllers',[]).controller('InsumoListController',function($scope,$state,popupService,$window,Insumo){

    $scope.insumos=Insumo.query();

    $scope.deleteInsumo=function(insumo){
        if(popupService.showPopup('Really delete this?')){
            insumo.$delete(function(){
                $window.location.href='/insumos/';
            }, function(response){alert(response.data.message)});
        }
    }

}).controller('InsumoViewController',function($scope,$stateParams,Insumo){

    $scope.insumo=Insumo.get({id:$stateParams.id});

}).controller('InsumoCreateController',function($scope,$state,$stateParams,Insumo){

    $scope.insumo=new Insumo();

    $scope.addInsumo=function(){
        $scope.insumo.$save(function(){
            $state.go('insumos');
        });
    }

}).controller('InsumoEditController',function($scope,$state,$stateParams,Insumo){

    $scope.updateInsumo=function(){
        $scope.insumo.$update(function(){
            $state.go('insumos');
        });
    };

    $scope.loadInsumo=function(){
        $scope.insumo=Insumo.get({id:$stateParams.id});
    };

    $scope.loadInsumo();
});