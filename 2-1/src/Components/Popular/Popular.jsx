import React from 'react'
import './Popular.css'
import all_products from '../Assets/all_product'
import Item from '../Items/Item'

const Popular = () => {
  return ( 
    <div className='popular'>
      <h1>Most Ordered</h1>
      <hr />
      <div className='popular-items'>
        {
          all_products.map((item, i) => {
            return <Item className="popular-item" key={i} id={item.id}  name={item.name} category = {item.category}  price={item.price} image={item.image} />
            })
        }
      </div>
    </div>
  );
}
 
export default Popular;
