import React, { useContext } from 'react'
import { ShopContext } from '../Context/ShopContext';
import Item from '../Components/Items/Item'
import { useParams } from 'react-router-dom';

const HandleSearch = () => {
    const { searchedItem } = useParams();
    const {all_product} = useContext(ShopContext);
    return ( 
        <div className='shop-cat-products'>
            {
                all_product.map((item,i)=>{
                    if(searchedItem == null) {
                        if(1 == 1){
                        return <Item key={i} id={item.id}  name={item.name} category = {item.category}  price={item.price} image={item.image}/>
                    }
                    }

                    if((item.name.toLowerCase()).includes(searchedItem.toLowerCase()) || (searchedItem.toLowerCase()).includes(item.name.toLowerCase()) || 
                    (item.category.toLowerCase()).includes(searchedItem.toLowerCase()) || (searchedItem.toLowerCase()).includes(item.category.toLowerCase())
                    ){
                        return <Item key={i} id={item.id}  name={item.name} category = {item.category}  price={item.price} image={item.image}/>
                    }
                    else{
                        return null;
                    }
                })
            }
        </div>
     );
}
 
export default HandleSearch;
