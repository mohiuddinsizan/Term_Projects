import React from 'react'
import './RelatedProducts.css'
import all_product from '../Assets/all_product';
import Item from '../Items/Item';
const RealtedProducts = (props) => {
    const {product} = props;
    return ( 
        <div className='relatedproducts'>
            <h1>
                Related Products
            </h1>
            <hr/>
            <div className='relatedproducts-item'>
                {
                    all_product.map((item,i)=>{
                        if(product.category===item.category && product.id!==item.id)
                            return <Item key={i} id={item.id}  name={item.name} category = {item.category}  price={item.price} image={item.image}/>
                        else return null;
                    })  
                }
            </div>
        </div>
     );
}
 
export default RealtedProducts;