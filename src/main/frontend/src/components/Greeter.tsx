import React from 'react';
import axios, { AxiosResponse } from 'axios';

type GreeterState = { message: string };

class Greeter extends React.Component<any, GreeterState> {
  constructor(props: any) {
    super(props);
    this.state = {
      message: 'Default message',
    };
  }

  componentDidMount() {
    this.getMessages();
  }

  private async getMessages() {
    const messages: AxiosResponse<string> = await axios.get('/api/message', { responseType: 'text' });
    this.setState({ message: messages.data });
  }

  render() {
    const { message } = this.state;
    return (
      <div>
        <span>
          Message:
          {' '}
          {message}
        </span>
      </div>
    );
  }
}

export default Greeter;
