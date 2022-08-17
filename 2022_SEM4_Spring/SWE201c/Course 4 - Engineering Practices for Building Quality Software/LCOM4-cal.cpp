#include "src/route.h"
Route::Route(std::string name, Stop *stops, double distances,
             int num_stops, PassengerGenerator *generator)
{
    // Constructors ignored in LCOM4 calculation
}
void Route::Update()
{
    GenerateNewPassengers();
    for (std::list<Stop *>::iterator it = stops_.begin();
         it != stops_.end(); it++)
    {
        (*it)->Update();
    }
}
bool Route::IsAtEnd() const
{
    return destination_stop_index_ >= num_stops_;
}
void Route::NextStop()
{
    destination_stop_index_++;
    if (destination_stop_index_ < num_stops_)
    {
        std::list<Stop *>::const_iterator iter = stops_.begin();
        std::advance(iter, destination_stop_index_);
        destination_stop_ = *iter;
    }
    else
    {
        destination_stop_ = (*stops_.end());
    }
}
Stop *Route::GetDestinationStop() const
{
    return destination_stop_;
}
double Route::GetTotalRouteDistance() const
{
    int total_distance = 0;
    for (std::list<double>::const_iterator iter = distances_between_.begin();
         iter != distances_between_.end();
         iter++)
    {
        total_distance += *iter;
    }
    return total_distance;
}
double Route::GetNextStopDistance() const
{
    std::list<double>::const_iterator iter = distances_between_.begin();
    std::advance(iter, destination_stop_index_ - 1);
    return iter; // resolving the iterator gets you the Stop from the list
}
int Route::GenerateNewPassengers()
{
    // returning number of passengers added by generator
    return generator_->GeneratePassengers();
}