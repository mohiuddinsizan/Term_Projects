import React, { useContext } from 'react'
import './ProductDisplay.css'
import { ShopContext } from '../../Context/ShopContext';
const ProductDisplay = (props) => {
    const {product} = props;
    const {addToCart} = useContext(ShopContext);
    return ( 

        <div className='productdisplay'>
            <div className='productdisplay-left'>
                <div className='productdisplay-img-list'>
                    <img src={product.image} alt="" />
                    <img src={product.image} alt="" />
                    <img src={product.image} alt="" />
                    <img src={product.image} alt="" />
                </div>
                <div className='productdisplay-img'>
                    <img className='productdisplay-main-img' src={product.image} alt="" />
                </div>
            </div>
            <div className='productdisplay-right'>
                <h1>{product.name}</h1>
                
                    <div className='productdisplay-right-price-old'> Price : {product.price} Tk.</div><br></br>
                    <div className='productdisplay-right-price-new'> Price : {product.price} Tk.</div>

                <div className='rating'>
                    <i className='fas fa-star'></i>
                    <i className='fas fa-star'></i>
                    <i className='fas fa-star'></i>
                    <i className='fas fa-star'></i>
                    <i className='fas fa-star'></i>
                </div>
                <div className='Authorname'><span>Author  :</span> Apurbo The Prem Kobi</div>
                <div className='Category'><span>Category  :</span> {product.category}</div>
                <div className='productdisplay-right-desc'>

                   <span> Description : </span>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also including versions of Lorem Ipsum.
                </div>
                <button onClick={()=>{addToCart(product.id)}}>ADD TO CART</button>
            </div>   
        </div>
     );
}
 
export default ProductDisplay;