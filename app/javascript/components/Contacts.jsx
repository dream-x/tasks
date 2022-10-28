import React, { useState } from 'react'

const Contacts = () => {
  const [contacts, setContacts] = useState([])

  const fetchContacts = async () => {
    const response = await fetch('http://localhost:3000/api/v1/contacts.json')
    const data = await response.json()
    setContacts(data)
  }

  return (
    <div>
        <h1>Hello! Dream-X</h1>
        <p>I'm the UI that allows you to intearact with the CRUD Api!</p>
        <button><a href='/new/contact'>New Contact</a></button>
        <br />
        <br />
        <button type="button" onClick={() => {window.open('http://localhost:3000/api/v1/contacts.csv')}} >Download CSV</button>
        {contacts.length==0 ?
        (
            <div>
                <h2>There are no contacts right now! You need to call the API GET request</h2>
                <button type="button" onClick={fetchContacts}>GET contacts</button>
            </div>
        )
        :
        contacts.map((c) => (
            <div key={c.id}>
                <h2><a href={`/contacts/${c.id}`}>{c.name}</a></h2>
            </div>
        ))}
    </div>
  )
}

export default Contacts