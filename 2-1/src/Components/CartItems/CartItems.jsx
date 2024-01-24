import React, { useContext } from 'react'
import './CartItems.css'
import { ShopContext } from '../../Context/ShopContext';
import remove_icon from '../Assets/remove_icon.png'
const CartItems = () => {
    const {getTotalCartAmount ,all_product,cartItems,removeFromCart} = useContext(ShopContext)
    return ( 
        <div className='cartitems'>
            <div className='cartitem-format-main'>
                <p>Books</p>
                <p>Category</p>
                <p>Price</p>
                <p>Quantity</p>
                <p>Total</p>
                <p>Remove</p>
            </div>
            <hr />
            {
            all_product.map((e)=>{
                if(cartItems[e.id]>0){
                    return             <div>
                    <div className='cartitem-format cartitem-format-main'>
                        <img src={e.image} alt ="" className='carticon' />
                        <p>{e.name}</p>
                        <p>{e.price} Tk</p>
                        <button className='cartitem-quantity'>{cartItems[e.id]}</button>
                        <p>{e.price*cartItems[e.id]} Tk</p>
                        <img src={remove_icon} onClick={()=>{removeFromCart(e.id)}} alt= "" className='removeicon' />
                    </div>
                    <hr />
                </div>
                }
                return null;
            })
            }
            <div className='cartitems-down'>
                <div className='total'>
                    <h1>Cart Total </h1>
                    <div>
                        <div className='cartitem-total'>
                            <p className='first'>SubTotal</p>
                            <p>{getTotalCartAmount()} Tk</p>
                        </div>
                        <hr/>
                        <div className='cartitem-total'>
                            <p className='first'>Shipping Fee</p>
                            <p>Free</p>
                        </div>
                        <hr/>
                        <div className='cartitem-total'>
                            <h3 className='first'>Total</h3>
                            <h3>{getTotalCartAmount()} Tk</h3>
                        </div>
                    </div>
                    <button>Checkout</button>
                </div>
            </div>

        </div>
     );
}
 
export default CartItems;