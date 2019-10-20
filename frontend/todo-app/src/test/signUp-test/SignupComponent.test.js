import React from 'react'
import SignupComponent from '../../components/signup/SignupComponent.jsx'
import SignupComponentMock from './SignupComponentMock.jsx'
import { mount, shallow, render } from 'enzyme'

// test if AllCourseComponent loads
describe('SignupComponent', () => {
  it('should render correctly in "debug" mode', () => {
    const component = shallow(<SignupComponent debug />);
  
    expect(component).toMatchSnapshot();
  });
});

// using AllCourseComponentMock for test
describe('SignupComponentMock', () => {
  // used to enroll into a course in the all courses list
  it('user is correct', () => {
    const component = mount(<SignupComponentMock debug />);
    
    component
    .find('#user')
    .simulate('focus')
    .simulate('change', { target: { value: 'Changed' } });

    component
    .find('#pass')
    .simulate('focus')
    .simulate('change', { target: { value: 'Changed' } });


    component
    .find('#enter')
    .simulate('click');

    expect(component).toMatchSnapshot();
    component.unmount();
  });

  // used to enroll unenroll from the all course list
  it('user is already taken', () => {
    const component = mount(<SignupComponentMock debug />);
    
    component
    .find('#user')
    .simulate('focus')
    .simulate('change', { target: { value: 'Sam' } });

    component
    .find('#pass')
    .simulate('focus')
    .simulate('change', { target: { value: 'Changed' } });


    component
    .find('#enter')
    .simulate('click');

    expect(component).toMatchSnapshot();
    component.unmount();
  });

  // used to unenroll from the my course list
  it('user too long', () => {
    const component = mount(<SignupComponentMock debug />);
    
    ccomponent
    .find('#user')
    .simulate('focus')
    .simulate('change', { target: { value: 'Changed1231323123131' } });

    component
    .find('#pass')
    .simulate('focus')
    .simulate('change', { target: { value: 'Changed' } });


    component
    .find('#enter')
    .simulate('click');

    expect(component).toMatchSnapshot();
    component.unmount();
  });
});