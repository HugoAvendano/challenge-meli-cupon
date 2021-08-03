# challenge-meli-cupon

## DESCRIPCION DE LA API

API REST  que dado una lista de item_id y el monto total retorna una  lista de items
que maximice el total gastado sin excederlo.

El mensaje que procesa es
  Post/coupon que recibe como parametro un json como el siguiente
      
      {
        "item_ids": ["MLA811600001","MLA764263123","MLA873557455","MLA931496304","MLA610585210"],
        "amount" : 22000.00
      }

      donde item_ids es una lista de ids de productos de Mercado libre y amount es el monto a maximizar
      



## INSTALACION
0. Tener instalado maven

1. Descargar el proyecto 
  
      git clone https://github.com/HugoAvendano/challenge-meli-cupon

2. Dentro del directorio donde se descargo el proyecto (C:\...\challenge-meli-cupon) ejecutar el siguiente comando
      
      mvn clean install

3. Posicionarse sobre la carpeta target
      
      cd target

4. Ejecutar el siguiente comando para levantar el servicio rest
    
      java -jar challenge-meli-cupon-0.0.1-SNAPSHOT


## HOST DE LA API EN CLOUD COMPUTING LIBRE
      
      https://challenge-meli-cupon.herokuapp.com/coupon/




