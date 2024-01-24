import React, { useContext } from 'react'
import './ShopCategory.css'
import { ShopContext } from '../Context/ShopContext';
import Item from '../Components/Items/Item'
const ShopCategory = (props) => {
    const {all_product} = useContext(ShopContext);
    return ( 
        <div>
            <div className='shop-category'>
                <img src = {props.banner} alt="" className='banner-images'/>
                <div className='shop-cat-idx'>
                    <p>
                        <span></span>
                    </p>
                </div>
            </div>
            <div className='shop-cat-products'>
                {
                    all_product.map((item,i)=>{
                        if(props.category === item.category){
                            return <Item key={i} id={item.id}  name={item.name} category = {item.category}  price={item.price} image={item.image}/>
                        }
                        else{
                            return null;
                        }
                    })
                }
            </div>
        </div>
     );
}
 
export default ShopCategory;
