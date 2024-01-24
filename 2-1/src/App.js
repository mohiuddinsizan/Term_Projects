import './App.css';
import Navbar from './Components/Navbar/Navbar';
import { BrowserRouter,Routes,Route} from 'react-router-dom';
import Shop from './Pages/Shop'
import Product from './Pages/Product'
import Cart from './Pages/Cart'
import Login from './Pages/Login'
import ShopCategory from './Pages/ShopCategory'
import Footer from './Components/Footer/Footer'
import horror from './Components/Assets/horror.webp'
import romance from './Components/Assets/romance.jpg'
import thriller from './Components/Assets/thriller.avif'
import story from './Components/Assets/story.webp'
import UserLogin from './Pages/UserLogin';
import HandleSearch from './Pages/HandleSearch';
function App() {
  return (
    <div>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path = '/' element = {<Shop/>}/>
          <Route path = '/story' element = {<ShopCategory banner = {story} category = 'Story'/>}/>
          <Route path = '/romance' element = {<ShopCategory banner = {romance} category = 'Romance'/>}/>
          <Route path = '/thriller' element = {<ShopCategory banner = {thriller} category = 'Thriller'/>}/>
          <Route path = '/horror' element = {<ShopCategory banner = {horror} category = 'Horror'/>}/>
          <Route path = '/product' element = {<Product/>}>
            <Route path = ':productId' element = {<Product/>}/>
          </Route>
          <Route path = '/cart' element = {<Cart/>}/>
          <Route path = '/login' element = {<Login/>}/>
          <Route path = '/userlogin' element = {<UserLogin/>}/>
          <Route path="/searched" element={<HandleSearch />}>
            <Route path=":searchedItem" element={<HandleSearch />} />
          </Route>
        </Routes>
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
