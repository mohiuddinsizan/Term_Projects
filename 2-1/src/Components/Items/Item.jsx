import React from 'react'
import './Item.css'
import '@fortawesome/fontawesome-free/css/all.min.css';
import {Link} from 'react-router-dom'
const Item = (props) => {
    return ( 
        <div className='item'>
            <Link to = {`/product/${props.id}`}><img onClick={window.scrollTo(0,0)} src={props.image} alt="" className='images'/></Link>
            <p>{props.name}</p>
            <p>Category : {props.category}</p>
            <div className='item-prices'>
                <div className='new-price'>Price : {props.price} Tk.</div>
                <div className='old-price'>Price : {props.price} Tk.</div>
            </div>
            <div className='rating'>
                <i className='fas fa-star'></i>
                <i className='fas fa-star'></i>
                <i className='fas fa-star'></i>
                <i className='fas fa-star'></i>
                <i className='fas fa-star'></i>
            </div>
        </div>
        
    );
}
 
export default Item;





// Item.js

// import React from 'react';
// import './Item.css';

// const Item = (props) => {
//   return ( 
//     <div className='item'>
//       <img src={props.image} alt="" />
//       <p>{props.name}</p>
//       <p>{props.price}</p>
//     </div>
//   );
// }
 
// export default Item;
