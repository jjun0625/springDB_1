import React from "react";
import { BrowserRouter, Route, Link, Router, Routes } from "react-router-dom";
import AddFrom from "./AddFrom";
import EditFrom from "./EditFrom";
import Item from "./Item";
import Items from "./Items";

function App() {
  return (
    <Routes>
      <Route path="/add" element={<AddFrom />} />
      <Route path="/edit/:id" element={<EditFrom />} />
      <Route path="/" element={<Items />} />
      <Route path="/:id" element={<Item />} />
    </Routes>
  );
}

export default App;
