import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Contact from './Contact';
import Contacts from './Contacts';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<Contacts />} />
        <Route exact path="/contacts/:id" element={<Contact />} />
      </Routes>
      </BrowserRouter>
  )
}

export default App