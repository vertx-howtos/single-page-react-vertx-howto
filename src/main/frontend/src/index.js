import React from 'react';
import ReactDOM from 'react-dom';

class Greeter extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      message: "Default message"
    }
  }

  componentDidMount() {
    fetch("/api/message")
      .then(response => response.text())
      .then(text => this.setState({message: text}));
  }

  render() {
    return (
      <div>
        <span>{this.state.message}</span>
      </div>
    );
  }
}

ReactDOM.render(
  <Greeter/>,
  document.getElementById('root')
);
