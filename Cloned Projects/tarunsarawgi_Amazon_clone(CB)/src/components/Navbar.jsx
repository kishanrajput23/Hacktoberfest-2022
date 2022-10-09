import React from "react";

function Navbar() {
  return (
    <nav class="d-flex none">
      <div class="nav_brand">
        <a href="#">
          <img src="./assets/logo.png" alt="" />
        </a>
      </div>
      <div class="search d-flex">
        <input type="text" />
        <i class="fas fa-search"></i>
      </div>
      <ul class="nav_menu d-flex">
        <li>
          <a href="#">
            Hello Guest <br />
            <span>Sign In</span>
          </a>
        </li>
        <li>
          <a href="#">
            Return <br />
            <span>Orders</span>
          </a>
        </li>
        <li>
          <a href="#">
            Your <br />
            <span>Prime</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class="fas fa-shopping-cart"></i> <span>0</span>
          </a>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;
