angular.module('ventaApp.controllers',[]).controller('VentaListController',function($scope,$state,popupService,$window,Venta){

    $scope.ventas=Venta.query();

}).controller('VentaViewController',function($scope,$stateParams,$timeout,Venta){
    $scope.venta = Venta.get({id:$stateParams.id}, function(venta){
         $scope.productos = venta.productos;
    });
   

}).controller('VentaCreateController',function($scope,$state,$stateParams,Venta,Producto){

    $scope.venta=new Venta();
    $scope.selectedProducto= {};
    $scope.productos=Producto.query({}, function(productos){
        $scope.selectedProducto.id = productos[0].id.toString();
    });
    $scope.addVenta=function(){
        $scope.venta.producto = $scope.selectedProducto.id;
        $scope.venta.$save(function(){
            $state.go('ventas');
        }, function(response){console.log(response);alert(response.data.message)});
    }

});