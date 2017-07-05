angular.module('productoApp.controllers',[]).controller('ProductoListController',function($scope,$state,popupService,$window,Producto){

    $scope.productos=Producto.query();

    $scope.deleteProducto=function(producto){
        if(popupService.showPopup('Really delete this?')){
            producto.$delete(function(){
                $window.location.href='/productos/';
            });
        }
    }

}).controller('ProductoViewController',function($scope,$stateParams,$timeout,Producto){
    $scope.producto = Producto.get({id:$stateParams.id}, function(producto){
         $scope.insumos = producto.insumos;
    });
   

}).controller('ProductoCreateController',function($scope,$state,$stateParams,Producto,Insumo){

    $scope.producto=new Producto();
    $scope.insumos=Insumo.query();
    $scope.producto.insumos = [];
    $scope.syncInsumos = function(bool, item){
        if(bool){
          // add item
          $scope.producto.insumos.push(item.id);
        } else {
          // remove item
            for(var i=0 ; i < $scope.producto.insumos.length; i++) {
                if($scope.producto.insumos[i] == item.id){
                 $scope.producto.insumos.splice(i,1);
                }
            }      
        }
    };
    
    $scope.isCheckedInsumo = function(id){
        var match = false;
        for(var i=0 ; i < $scope.producto.insumos.length; i++) {
            if($scope.producto.insumos[i] == id){
                match = true;
            }
        }
        return match;
    };
  
    $scope.addProducto=function(){
        $scope.producto.$save(function(){
            $state.go('productos');
        });
    }

}).controller('ProductoEditController',function($scope,$state,$stateParams,Producto, Insumo){

    $scope.loadProducto=function(){
        $scope.producto=Producto.get({id:$stateParams.id}, function(producto){
            insumos = [];
            for(var i=0 ; i < producto.insumos.length; i++){
                insumos.push(producto.insumos[i].id);
            }
            producto.insumos = insumos;
        });
    };

    $scope.loadProducto();
    
    $scope.insumos=Insumo.query();
    
    $scope.syncInsumos = function(bool, item){
        if(bool){
          // add item
          $scope.producto.insumos.push(item.id);
        } else {
          // remove item
            for(var i=0 ; i < $scope.producto.insumos.length; i++) {
                if($scope.producto.insumos[i] == item.id){
                 $scope.producto.insumos.splice(i,1);
                }
            }      
        }
    };
    
    $scope.isCheckedInsumo = function(id){
        var match = false;
        for(var i=0 ; i < $scope.producto.insumos.length; i++) {
            if($scope.producto.insumos[i] == id){
                match = true;
            }
        }
        return match;
    };

    $scope.updateProducto=function(){
        $scope.producto.$update(function(){
            $state.go('productos');
        });
    };


});