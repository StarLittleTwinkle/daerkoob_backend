import { BrowserRouter, Switch, Route } from "react-router-dom";

import React from "react";
import Home from "../pages/Home";
import Mypage from "pages/Mypage";
import Auth from "../pages/Auth";
import Review from "../pages/Review";
import Navbar from "components/Navbar";
import Footer from "components/Footer";
import Notice from "../pages/Notice/index";

const Router = () => {
  return (
    <BrowserRouter>
      <Navbar />
      <Switch>
        <Route exact path="/" component={Home} />
        <Route path="/mypage" component={Mypage} />
        <Route path="/auth" component={Auth} />
        <Route path="/review" component={Review} />
        <Route path="/notice" component={Notice} />
        <Route path="/transcription" component={Review} />
      </Switch>
      <Footer />
    </BrowserRouter>
  );
};

export default Router;
