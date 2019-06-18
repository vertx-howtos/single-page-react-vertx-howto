import React from 'react';
import ReactDOM from 'react-dom';

class Greeter extends React.Component { // <1>

  constructor(props) {
    super(props);
    this.state = { // <2>
      message: "Default message"
    }
  }

  componentDidMount() { // <5>
    fetch("/api/message")
      .then(response => response.text())
      .then(text => this.setState({message: text}));
  }

  render() { // <3>
    return (
      <div>
        <span>{this.state.message}</span>
      </div>
    );
  }
}

ReactDOM.render( // <4>
  <Greeter/>,
  document.getElementById('root')
);
