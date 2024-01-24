import React, { useContext } from 'react'
import './Navbar.css'
import logo from '../Assets/logo.png'
import cart from '../Assets/cart.jpg'
import {useState} from 'react'
import { Link } from 'react-router-dom'
import { ShopContext } from '../../Context/ShopContext'

const Navbar = () => {
    const [menu,setmenu] = useState("story");
    const {getTotalCartItems} = useContext(ShopContext);

    const [searchInput, setSearchInput] = useState('');

    const handleInputChange = (e) => {
      setSearchInput(e.target.value);
    };
  
    const handleSearch = (e) => {
      e.preventDefault();
      // Do something with the searchInput, e.g., send it to a search function or component
      //console.log('Search input:', searchInput);
    };

    return ( 
        <div className='navbar'>
            <div className='nav-logo'>
                <Link style={{textDecoration:'none'}} to = '/'><img src = {logo} alt = "" width="50" height="50"></img></Link>
                <p><Link style={{textDecoration:'none',color:'crimson'}} to = '/'>BookSphere</Link></p>
            </div>
            <ul className='nav-menu'>
                <li onClick={()=>{setmenu("story")}}> <Link style={{textDecoration:'none',color:'crimson'}} to = '/story'>Story</Link>  {menu === "story"?<hr/>:<></>}</li>
                <li onClick={()=>{setmenu("thriller")}}><Link  style={{textDecoration:'none',color:'crimson'}} to = '/thriller'>Thriller</Link> {menu === "thriller"?<hr/>:<></>}</li>
                <li onClick={()=>{setmenu("romance")}}><Link  style={{textDecoration:'none',color:'crimson'}} to = '/romance'>Romance</Link> {menu === "romance"?<hr/>:<></>}</li>
                <li onClick={()=>{setmenu("horror")}}><Link  style={{textDecoration:'none',color:'crimson'}} to = '/horror'>Horror</Link> </li>
            </ul>
            <div className='search-bar'>
                <form onSubmit={handleSearch}>
                    <input
                    type="text"
                    placeholder="Search..."
                    value={searchInput}
                    onChange={handleInputChange}
                    />
                    <button type="submit" > <Link  style={{textDecoration:'none',color:'white'}} to = {`/searched/${searchInput}`}>Search</Link>{menu === "searched"?<hr/>:<></>} </button>
                </form>
            </div>    
            <div className='nav-login-cart'>
                <Link style={{textDecoration:'none'}}  to = '/login'><button>Login</button></Link>
                <Link style={{textDecoration:'none'}}  to='/cart'><img src = {cart} alt="" width="50" height="50"></img></Link> 
                <div className='nav-cart-count'>{getTotalCartItems()}</div>
            </div>      
        </div>
     );
}
 
export default Navbar;



